package com.libraryservice.libraryservice.api.exceptions;

public class BookIsPresentException extends Throwable{
    public BookIsPresentException(String msg){
        super(msg);
    }
}
