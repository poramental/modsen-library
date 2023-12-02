package com.libraryservice.libraryservice.api.exceptions;

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

    @ExceptionHandler(MessageSenderException.class)
    public ResponseEntity<AppError> bookRecordServiceMessageSenderException(MessageSenderException exp){
        return new ResponseEntity<>(new AppError(HttpStatus.SERVICE_UNAVAILABLE,exp.getMessage()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BookIsPresentException.class)
    public ResponseEntity<AppError> bookIsPresentExceptionHandler(BookIsPresentException exp){
        return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT,exp.getMessage()),
                HttpStatus.CONFLICT);
    }
}
