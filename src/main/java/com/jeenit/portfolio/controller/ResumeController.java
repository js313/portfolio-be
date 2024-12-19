package com.jeenit.portfolio.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    private final Path resumePath;
    private final ResourceLoader resourceLoader;

    @Autowired
    public ResumeController(@Value("${resume.file-path}") String resumeFilePath, ResourceLoader resourceLoader) {
        this.resumePath = Paths.get(resumeFilePath);
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadResume() {
        try {
            Resource fileResource = resourceLoader.getResource("file:" + resumePath.toAbsolutePath());
            if(!fileResource.exists()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf")
                    .body(fileResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null ||!file.getOriginalFilename().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("Invalid file. Please upload a PDF file.");
        }

        try {
            Files.createDirectories(resumePath.getParent());
            Files.write(resumePath, file.getBytes());
            return ResponseEntity.ok("Resume uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload resume. Error: " + e.getMessage());
        }
    }
}
