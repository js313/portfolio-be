package com.jeenit.portfolio.controller;

import com.jeenit.portfolio.model.Project;
import com.jeenit.portfolio.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public ResponseEntity<List<Project>> getProjects(@RequestParam(required = false) String type) {
        List<Project> projects = new ArrayList<>();
        if(type == null || type.equals("all"))
            projects = projectService.getAllProjects();
        else
            projects = projectService.getProjectsByType(type);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }
}
