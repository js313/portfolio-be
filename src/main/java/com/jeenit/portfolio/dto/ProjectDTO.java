package com.jeenit.portfolio.dto;

import com.jeenit.portfolio.model.Project;
import com.jeenit.portfolio.model.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO implements DTO<Project> {
    @NotBlank
    private String name;

    private String image;

    @Size(max = 255, message = "description too long")
    private String description;

    @NotBlank
    private String githubLink;

    private String itchIoLink;

    private String projectLink;

    private boolean highlight;

    private boolean p5Sketch;

    @NotNull
    private ProjectType type;

    @Override
    public Project toEntity() {
        Project project = new Project();
        project.setName(this.name);
        project.setImage(this.image);
        project.setDescription(this.description);
        project.setGithubLink(this.githubLink);
        project.setItchIoLink(this.itchIoLink);
        project.setProjectLink(this.projectLink);
        project.setType(this.type);
        project.setHighlight(this.highlight);
        project.setP5Sketch(this.p5Sketch);

        return project;
    }
}
