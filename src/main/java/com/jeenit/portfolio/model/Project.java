package com.jeenit.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Project extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    private String image; // Optional: URL of the project image

    @Column(length = 1000) // In case of long descriptions
    private String description;

    @NotBlank
    private String githubLink;

    private String itchIoLink; // Optional: Link to itch.io

    private String projectLink; // Optional: Link to hosted project

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    @NotNull
    private ProjectType type;
}
