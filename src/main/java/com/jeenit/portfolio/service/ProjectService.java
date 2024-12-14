package com.jeenit.portfolio.service;

import com.jeenit.portfolio.model.Project;
import com.jeenit.portfolio.model.ProjectType;
import com.jeenit.portfolio.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectService {
    final ProjectRepository projectRepository;
    final ProjectTypeService projectTypeService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectTypeService projectTypeService) {
        this.projectRepository = projectRepository;
        this.projectTypeService = projectTypeService;
    }

    public List<Project> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByType(String type) {
        return projectRepository.findByTypeName(type);
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project createNewProject(Project project) throws IllegalArgumentException {
        ProjectType projectType = projectTypeService.getProjectType(project.getType().getId());
        if(projectType == null) {
            throw new IllegalArgumentException("Project Type not Found");
        }
        project.setType(projectType);
        return projectRepository.save(project);
    }
}
