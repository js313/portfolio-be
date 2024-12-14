package com.jeenit.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    @Transient
    private String confirmPassword;
}
