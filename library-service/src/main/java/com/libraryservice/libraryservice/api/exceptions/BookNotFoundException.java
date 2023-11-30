package com.libraryservice.libraryservice.api.exceptions;

public class BookNotFoundException extends Throwable{
    public BookNotFoundException(String msg){
        super(msg);
    }
}
