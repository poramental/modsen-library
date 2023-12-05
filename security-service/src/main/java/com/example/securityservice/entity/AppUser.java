package com.example.securityservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;
import lombok.Data;
@Entity(name = "app_user")
@Data
public class AppUser {
    @Id()
    @Column(name = "user_id")
    private UUID userId;
    private String name;
    private String email;
    private String password;
}
