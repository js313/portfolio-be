package com.jeenit.portfolio.service;

import com.jeenit.portfolio.model.Project;
import com.jeenit.portfolio.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

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
}
