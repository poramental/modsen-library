package com.example.securityservice.config;


import com.example.securityservice.entity.AppUser;
import com.example.securityservice.repo.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userCredentialRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userCredential =  userCredentialRepository.findByName(username);
        return userCredential.map(AppUserDetails::new).orElseThrow(()->
                new UsernameNotFoundException(String.format("user with name : %s not found ",username)));
    }
}