package com.jeenit.portfolio.service;

import com.jeenit.portfolio.model.Project;
import com.jeenit.portfolio.model.ProjectType;
import com.jeenit.portfolio.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.apache.hc.client5.http.classic.methods.HttpGet;

import java.io.IOException;
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
        return projectRepository.findAll(Sort.by(Sort.Order.desc("highlight"), Sort.Order.desc("type.priority")));
    }

    public List<Project> getProjectsByType(String type) {
        return projectRepository.findByTypeName(type, Sort.by(Sort.Order.desc("highlight"), Sort.Order.desc("type.priority")));
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

    public Project updateProjectPartial(int id, Project newProjectData) {
        Project existingProject = getProjectById(id);
        if (existingProject == null) {
            throw new IllegalArgumentException("Project not found with ID: " + id);
        }

        if (newProjectData.getName() != null) {
            existingProject.setName(newProjectData.getName());
        }
        if (newProjectData.getImage() != null) {
            existingProject.setImage(newProjectData.getImage());
        }
        if (newProjectData.getDescription() != null) {
            existingProject.setDescription(newProjectData.getDescription());
        }
        if (newProjectData.getGithubLink() != null) {
            existingProject.setGithubLink(newProjectData.getGithubLink());
        }
        if (newProjectData.getItchIoLink() != null) {
            existingProject.setItchIoLink(newProjectData.getItchIoLink());
        }
        if (newProjectData.getProjectLink() != null) {
            existingProject.setProjectLink(newProjectData.getProjectLink());
        }
        if (newProjectData.getType() != null) {
            existingProject.setType(newProjectData.getType());
        }

        existingProject.setHighlight(newProjectData.isHighlight());
        existingProject.setP5Sketch(newProjectData.isP5Sketch());

        return projectRepository.save(existingProject);
    }

    private String constructRawGithubUrl(String githubUrl) {
        return githubUrl.replace("github.com", "raw.githubusercontent.com")
                .replace("/tree/", "/")
                + "/build/index.min.js";
    }

    private String fetchIndexMinJs(String rawUrl) {
        String response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(rawUrl);
            response = httpClient.execute(request, new BasicHttpClientResponseHandler());
        } catch (IOException e) {
            log.error("{} {}", rawUrl, e.getMessage());
        }
        return response;
    }

    public String getP5SketchFile(int projectId) {
        Project project = getProjectById(projectId);
        if (project == null || !project.isP5Sketch()) {
            return null;
        }

        String githubUrl = project.getGithubLink();
        if(!githubUrl.contains("github.com")) {
            return null;
        }

        String rawGithubUrl = constructRawGithubUrl(githubUrl);
        return fetchIndexMinJs(rawGithubUrl);
    }
}
