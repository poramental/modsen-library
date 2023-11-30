package com.libraryservice.libraryservice.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class AppError {
    private HttpStatus status;
    private String message;
}
