package com.libraryservice.libraryservice.api;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryservice.libraryservice.api.entity.Book;
import com.libraryservice.libraryservice.api.exceptions.BookRecordServiceMessageSenderException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class BookRecordServiceMessageSender {

        public  HttpStatus sendMessageToAddBookInRecordService(Book book) {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost req = new HttpPost("http://localhost:8083/add-book");
            try {
                HttpResponse resp = httpClient.execute(req);
                String json = EntityUtils.toString(resp.getEntity());
                ObjectMapper objectMapper = new ObjectMapper();
                HttpStatus status = objectMapper.readValue(json, new TypeReference<HttpStatus>() {});
                return status;
            }catch (IOException e){
                log.info(e.getMessage());
                return HttpStatus.SERVICE_UNAVAILABLE;
            }
        }

        private  <T,V extends HttpUriRequest> T sendMessage(V req)
                throws BookRecordServiceMessageSenderException{
            HttpClient httpClient = HttpClients.createDefault();
            try {
                HttpResponse resp = httpClient.execute(req);
                String json = EntityUtils.toString(resp.getEntity());

                ObjectMapper objectMapper = new ObjectMapper();
                T status = objectMapper.readValue(json, new TypeReference<T>() {});
                return status;
            }catch (IOException e){
                log.info(e.getMessage());
                throw new BookRecordServiceMessageSenderException("the service is not responding.");
            }
        }

    private  <T> T sendPostMessage(HttpPost req) throws BookRecordServiceMessageSenderException{{
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse resp = httpClient.execute(req);
            String json = EntityUtils.toString(resp.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            T status = objectMapper.readValue(json, new TypeReference<T>() {});
            return status;
        }catch (IOException e){
            log.info(e.getMessage());
            throw new BookRecordServiceMessageSenderException("the service is not responding.");
        }
    }
        }

        public HttpStatus sendMessageToDeleteBook(UUID id){

            HttpClient httpClient = HttpClients.createDefault();
            HttpDelete req = new HttpDelete("http://localhost:8083/" + id);
            try {
                HttpResponse resp = httpClient.execute(req);
                String json = EntityUtils.toString(resp.getEntity());
                System.out.println(json);
                ObjectMapper objectMapper = new ObjectMapper();
                HttpStatus status = objectMapper.readValue(json, new TypeReference<HttpStatus>() {});
                return status;
            }catch (IOException e){
                log.info(e.getMessage());
                return HttpStatus.SERVICE_UNAVAILABLE;
            }
        }

        public List<Book> sendMessageToGetAllBooks(){

        }
}
