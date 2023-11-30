package com.example.bookrecordservice.exceptions;

public class BookNotFoundException extends Throwable{
    public BookNotFoundException(String msg){
        super(msg);
    }
}
