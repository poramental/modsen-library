package com.libraryservice.libraryservice.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryservice.libraryservice.api.exceptions.MessageSenderException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor
public class MessageSender <V extends HttpUriRequest>{


    public HashMap<String,String> sendMessage(V req)
            throws MessageSenderException {
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse resp = httpClient.execute(req);
            String json = EntityUtils.toString(resp.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            log.info(json);
            if(json.contains("OK")){
                HashMap<String,String> map = new HashMap<>();
                map.put("status","OK");
                return map;
            }
            return objectMapper.readValue(json, new TypeReference<>() {});
        }catch (IOException e){
            log.info(e.getMessage());
            throw new MessageSenderException("the service is not responding.");
        }
    }
}
