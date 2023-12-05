package com.example.securityservice.controller;

import com.example.securityservice.dto.AuthReq;
import com.example.securityservice.entity.AppUser;
import com.example.securityservice.exceptions.UserAuthNotFoundException;
import com.example.securityservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody AppUser userCredential){
        log.info("asd");
        return authService.saveUser(userCredential);
    }
    @PostMapping("/token")
    public String getToken(@RequestBody AuthReq authRequest) throws UserAuthNotFoundException {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated())
            return authService.generateToken(authRequest.getUsername());
        else
            throw new UserAuthNotFoundException("user not registered");
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        return "Token is Valid";
    }
}