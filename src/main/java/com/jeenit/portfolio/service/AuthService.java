package com.jeenit.portfolio.service;

import com.jeenit.portfolio.model.Admin;
import com.jeenit.portfolio.repository.AdminRepository;
import com.jeenit.portfolio.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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

    public String login(String name, String password) throws RuntimeException {
        Admin admin = adminRepository.findByName(name).orElse(null);
        if (admin != null && name.equals(admin.getName()) && passwordEncoder.matches(password, admin.getPassword())) {
            return jwtTokenUtil.generateToken(name);
        }
        throw new BadCredentialsException("Invalid credentials");
    }

    public String signup(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Admin newAdmin = adminRepository.save(admin);
        return jwtTokenUtil.generateToken(newAdmin.getName());
    }
}
