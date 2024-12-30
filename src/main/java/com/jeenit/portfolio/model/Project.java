package com.jeenit.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    private String image;

    @Column(length = 255)
    private String description;

    @NotBlank
    private String githubLink;

    private String itchIoLink;

    private String projectLink;

    private boolean highlight;

    @Column(name = "p5_sketch")
    private boolean p5Sketch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    @NotNull
    private ProjectType type;
}
