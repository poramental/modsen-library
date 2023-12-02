package com.libraryservice.libraryservice.api;


import com.libraryservice.libraryservice.api.entity.Book;
import com.libraryservice.libraryservice.api.exceptions.MessageSenderException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class BookRecordServiceMessageSender {


    private final String baseUrl = "http://localhost:8765/book-record-service";


    public  HttpStatus sendMessageToAddBookInRecordService(Book book) throws MessageSenderException,
            UnsupportedEncodingException {
        HttpPost req = new HttpPost(baseUrl);
        req.setHeader("Accept", "application/json");
        req.setHeader("Content-type", "application/json");
        req.setEntity(new StringEntity(book.toJsonString()));
        return new MessageSender<HttpStatus,HttpPost>().sendMessage(req);
    }




    public HttpStatus sendMessageToDeleteBook(UUID id) throws MessageSenderException {
        return new MessageSender<HttpStatus,HttpDelete>().sendMessage(new HttpDelete(baseUrl + "/" + id));
    }


}
