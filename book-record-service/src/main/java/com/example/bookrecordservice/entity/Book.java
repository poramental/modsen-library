package com.example.bookrecordservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity(name = "books")
@Data
public class Book {
    @Id()
    @Column(name = "book_id")
    private UUID bookId;
    private String ISBN;
    private String name;
    private String author;
    private String description;
    private String genre;
}
