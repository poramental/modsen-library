package com.libraryservice.libraryservice.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class BookDto {
    private String ISBN;
    private String name;
    private String author;
    private String description;
    private String genre;
}
