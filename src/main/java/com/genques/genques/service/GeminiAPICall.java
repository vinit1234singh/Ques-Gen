package com.genques.genques.service;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import org.slf4j.Logger;
import com.genques.genques.model.ContentRequest;
import com.genques.genques.model.PartWrapper;
import com.genques.genques.model.TextPart;

import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class GeminiAPICall {

    private static final Logger logger = LoggerFactory.getLogger(GeminiAPICall.class);


    @Autowired
    GetParseData getParseData;

      final HttpHeaders headers;
        public GeminiAPICall() {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); 
        }

    public String googleGemniAPICall(String descrition){
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyCy0Wx_76QmnxD2OkudtIrvtVaifXptF_M";

        logger.info("Inside  Gemini Call");
        if(getParseData.getParseData().equals("Data is Empty")){
            return "Parse Data is not present";
        }
        else if (getParseData.getParseData().contains("4")) {
            return "Client Side Error";
        }
        // Create request body
        TextPart textPart = new TextPart(getParseData.getParseData()+descrition);
        PartWrapper partWrapper = new PartWrapper(Collections.singletonList(textPart));
        ContentRequest request = new ContentRequest(Collections.singletonList(partWrapper));

        // Wrap in HttpEntity
        HttpEntity<ContentRequest> httpEntity = new HttpEntity<>(request, headers);

        // Call API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);

        // Print response
        
        logger.info("Gemini call Exit");
        return response.getBody();

    }
    
}
