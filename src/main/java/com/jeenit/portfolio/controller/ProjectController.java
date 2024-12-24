package com.jeenit.portfolio.controller;

import com.jeenit.portfolio.dto.ProjectDTO;
import com.jeenit.portfolio.model.Project;
import com.jeenit.portfolio.service.ProjectService;
import com.jeenit.portfolio.service.ProjectTypeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ProjectController(ProjectService projectService, ProjectTypeService projectTypeService) {
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

    @GetMapping("/{id}/p5sketch")
    public ResponseEntity<String> getP5SketchFile(@PathVariable int id) {
        String sketchContent = projectService.getP5SketchFile(id);
        if(sketchContent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sketchContent);
    }

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody @Valid ProjectDTO projectDTO) {
        Project project = projectDTO.toEntity();
        try {
            project = projectService.createNewProject(project);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> patchProject(@PathVariable int id, @RequestBody ProjectDTO projectDTO) {
        try {
            Project newProjectData = projectDTO.toEntity();
            Project updatedProject = projectService.updateProjectPartial(id, newProjectData);
            return ResponseEntity.ok(updatedProject);
        } catch (IllegalArgumentException e) {
            log.error("Error updating project with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
