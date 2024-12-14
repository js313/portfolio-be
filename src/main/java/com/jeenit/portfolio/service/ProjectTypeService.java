package com.jeenit.portfolio.service;

import com.jeenit.portfolio.model.ProjectType;
import com.jeenit.portfolio.repository.ProjectTypeRepository;
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
}
