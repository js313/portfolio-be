package com.jeenit.portfolio.controller;

import com.jeenit.portfolio.model.ProjectType;
import com.jeenit.portfolio.service.ProjectTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
