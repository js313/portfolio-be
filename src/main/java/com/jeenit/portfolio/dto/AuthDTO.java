package com.jeenit.portfolio.dto;

import com.jeenit.portfolio.model.Admin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO implements DTO<Admin> {
    @NotBlank
    @Size(min = 3, message = "name too short")
    @Size(max = 255, message = "name too long}")
    private String name;

    @NotBlank
    @Size(min = 8, message = "password too short")
    private String password;

    @Override
    public Admin toEntity() {
        Admin admin = new Admin();
        admin.setName(this.name);
        admin.setPassword(this.password);
        return admin;
    }
}
