package com.jeenit.portfolio.service;

import com.jeenit.portfolio.model.ProjectType;
import com.jeenit.portfolio.repository.ProjectTypeRepository;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectTypeService {
    final ProjectTypeRepository projectTypeRepository;

    @Autowired
    public ProjectTypeService(ProjectTypeRepository projectTypeRepository) {
        this.projectTypeRepository = projectTypeRepository;
    }

    public List<ProjectType> getAllProjectTypes() {
        return projectTypeRepository.findAll();
    }

    public ProjectType getProjectType(int id) {
        return projectTypeRepository.findById(id).orElse(null);
    }

    public ProjectType createNewProjectType(ProjectType projectType) {
        if(projectTypeRepository.existsByName(projectType.getName())) {
            throw new EntityExistsException("Project Type with name: '" + projectType.getName() + "' already exists.");
        }
        return projectTypeRepository.save(projectType);
    }

    public ProjectType updateProjectType(int id, ProjectType newProjectType) {
        ProjectType existingProjectType = getProjectType(id);

        if (newProjectType.getName() != null && !newProjectType.getName().equals(existingProjectType.getName())) {
            if (projectTypeRepository.existsByName(newProjectType.getName())) {
                throw new EntityExistsException("Project Type with name: '" + newProjectType.getName() + "' already exists.");
            }
            existingProjectType.setName(newProjectType.getName());
        }

        if (newProjectType.getDisplayName() != null) {
            existingProjectType.setDisplayName(newProjectType.getDisplayName());
        }

        if (newProjectType.getPriority() != 0) { // Check to ensure valid non-default integer value
            existingProjectType.setPriority(newProjectType.getPriority());
        }

        return projectTypeRepository.save(existingProjectType);
    }
}
