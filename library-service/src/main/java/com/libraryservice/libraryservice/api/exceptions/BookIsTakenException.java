package com.libraryservice.libraryservice.api.exceptions;

public class BookIsTakenException extends Throwable{
    public BookIsTakenException(String msg){
        super(msg);
    }
}
