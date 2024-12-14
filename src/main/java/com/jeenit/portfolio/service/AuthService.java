package com.jeenit.portfolio.service;

import com.jeenit.portfolio.model.Admin;
import com.jeenit.portfolio.repository.AdminRepository;
import com.jeenit.portfolio.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {
    final AdminRepository adminRepository;
    final PasswordEncoder passwordEncoder;
    final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    Admin getAdminByName(String name) {
        return adminRepository.findByName(name).orElse(null);
    }

    public String login(String name, String password) throws RuntimeException {
        Admin admin = getAdminByName(name);
        if (admin != null && name.equals(admin.getName()) && passwordEncoder.matches(password, admin.getPassword())) {
            return jwtTokenUtil.generateToken(name);
        }
        throw new RuntimeException("Invalid credentials");
    }
}
