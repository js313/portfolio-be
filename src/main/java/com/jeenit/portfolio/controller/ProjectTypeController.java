package com.jeenit.portfolio.controller;

import com.jeenit.portfolio.dto.ProjectTypeDTO;
import com.jeenit.portfolio.model.ProjectType;
import com.jeenit.portfolio.service.ProjectTypeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/project-types")
public class ProjectTypeController {
    final ProjectTypeService projectTypeService;

    @Autowired
    public ProjectTypeController(ProjectTypeService projectTypeService) {
        this.projectTypeService = projectTypeService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProjectType>> getAllProjectTypes() {
        return ResponseEntity.ok(projectTypeService.getAllProjectTypes());
    }

    @PostMapping("")
    public ResponseEntity<ProjectType> createNewProjectType(@RequestBody @Valid ProjectTypeDTO projectTypeDTO) throws SQLIntegrityConstraintViolationException {
        ProjectType projectType = projectTypeDTO.toEntity();
        ProjectType newProjectType = projectTypeService.createNewProjectType(projectType);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProjectType);
    }
}
