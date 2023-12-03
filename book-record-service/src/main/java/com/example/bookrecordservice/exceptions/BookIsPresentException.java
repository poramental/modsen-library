package com.example.bookrecordservice.exceptions;

public class BookIsPresentException extends Throwable{
    public BookIsPresentException(String msg){
        super(msg);
    }
}
