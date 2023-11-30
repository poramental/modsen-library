package com.example.bookrecordservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<AppError> bookNotFoundException(BookNotFoundException exp){
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND,exp.getMessage()),HttpStatus.NOT_FOUND);
    }
}
