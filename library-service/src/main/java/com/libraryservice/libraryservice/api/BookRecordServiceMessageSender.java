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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class BookRecordServiceMessageSender {


    private final String baseUrl = "http://localhost:8765/rest/books-record";


    public  HttpStatus sendMessageToAddBookInRecordService(Book book, String token) throws MessageSenderException,
            UnsupportedEncodingException {
        HttpPost req = new HttpPost(baseUrl + "/add-book");
        req.setHeader("Accept", "application/json");
        req.setHeader("Content-type", "application/json");
        req.setHeader("Authorization", token);
        req.setEntity(new StringEntity(book.toJsonString()));
        HashMap<String, String> resp = new MessageSender<HttpPost>().sendMessage(req);
        log.info(resp.toString());
        if(resp.get("status").equals("CONFLICT")){
            return HttpStatus.CONFLICT;
        }return HttpStatus.OK;
    }



    public HttpStatus sendMessageToDeleteBook(String ISBN, String token) throws MessageSenderException {
        HttpDelete req = new HttpDelete(baseUrl + "/delete-book-by-isbn/" + ISBN);
        req.setHeader("Authorization", token);
        HashMap<String, String> resp = new MessageSender<HttpDelete>().sendMessage(req);
        log.info(resp.toString());
        if(resp.get("status").equals("NOT_FOUND")){
            return HttpStatus.NOT_FOUND;
        }return HttpStatus.OK;
    }


}
