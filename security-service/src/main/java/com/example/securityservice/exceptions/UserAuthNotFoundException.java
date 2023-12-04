package com.example.securityservice.exceptions;

public class UserAuthNotFoundException extends Throwable {
    public UserAuthNotFoundException(String userNotRegistered) {
        super(userNotRegistered);
    }
}
