package com.example.securityservice.service;

import com.example.securityservice.entity.AppUser;
import com.example.securityservice.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private AppUserRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(AppUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getName());
        user.setUserId(UUID.randomUUID());
        userCredentialRepository.save(user);
        return "user add to the system";
    }

    public String generateToken(String userName) {
        return jwtService.generateToken(userName);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}