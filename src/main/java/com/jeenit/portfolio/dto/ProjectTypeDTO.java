package com.jeenit.portfolio.dto;

import com.jeenit.portfolio.model.ProjectType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectTypeDTO implements DTO<ProjectType> {
    @NotBlank
    @Size(min = 3, message = "name too short")
    @Size(max = 255, message = "name too long")
    private String name;

    @NotBlank
    @Size(min = 3, message = "display name too short")
    @Size(max = 255, message = "display name too long")
    private String displayName;

    @Override
    public ProjectType toEntity() {
        ProjectType projectType = new ProjectType();
        projectType.setName(this.name);
        projectType.setDisplayName(this.displayName);
        return projectType;
    }
}
