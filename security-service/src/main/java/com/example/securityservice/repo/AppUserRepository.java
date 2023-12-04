package com.example.securityservice.repo;

import com.example.securityservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser,UUID> {
    Optional<AppUser> findByName(String username);
}
