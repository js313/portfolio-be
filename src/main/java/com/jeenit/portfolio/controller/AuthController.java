package com.jeenit.portfolio.controller;

import com.jeenit.portfolio.dto.AuthDTO;
import com.jeenit.portfolio.model.Admin;
import com.jeenit.portfolio.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid AuthDTO authDTO) {
        System.out.println(authDTO);
        Admin admin = authDTO.toEntity();
        String name = admin.getName(), password = admin.getPassword();
        Map<String, String> responseBody = new HashMap<>();
        try {
            String token = authService.login(name, password);
            responseBody.put("token", token);
        } catch(BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody @Valid AuthDTO authDTO) {
        Admin admin = authDTO.toEntity();
        Map<String, String> responseBody = new HashMap<>();
        try {
            String token = authService.signup(admin);
            responseBody.put("token", token);
        } catch(BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(responseBody);
    }
}
