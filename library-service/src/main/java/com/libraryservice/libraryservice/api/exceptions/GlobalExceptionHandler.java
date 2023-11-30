package com.libraryservice.libraryservice.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<AppError> bookNotFoundException(BookNotFoundException exp){
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND,exp.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookRecordServiceMessageSenderException.class)
    public ResponseEntity<AppError> bookRecordServiceMessageSenderException(BookRecordServiceMessageSenderException exp){
        return new ResponseEntity<>(new AppError(HttpStatus.SERVICE_UNAVAILABLE,exp.getMessage()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }
}
