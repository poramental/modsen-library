package com.example.securityservice.service;

import com.example.securityservice.entity.AppUser;
import com.example.securityservice.repo.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository userCredentialRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String saveUser(AppUser credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        System.out.println(credential.getName());
        userCredentialRepository.save(credential);
        return "user add to the system";
    }

    public String generateToken(String userName) {
        return jwtService.generateToken(userName);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}