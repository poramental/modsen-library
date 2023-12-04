package com.example.securityservice.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class AppUser {
    @Id()
    @Column(name = "user_id")
    private UUID userId;
    private String name;
    private String email;
    private String password;
}
