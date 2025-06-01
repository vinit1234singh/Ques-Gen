package com.genques.genques.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import com.genques.genques.model.ContentRequest;
import com.genques.genques.model.PartWrapper;
import com.genques.genques.model.TextPart;

@Service
@Component
public class GeminiAPICall {

    @Autowired
    GetParseData getParseData;

      final HttpHeaders headers;
        public GeminiAPICall() {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); 
        }

    public String googleGemniAPICall(){
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyCy0Wx_76QmnxD2OkudtIrvtVaifXptF_M";

        // Create request body
        TextPart textPart = new TextPart(getParseData.getParseData()+"Generate five best Question");
        PartWrapper partWrapper = new PartWrapper(Collections.singletonList(textPart));
        ContentRequest request = new ContentRequest(Collections.singletonList(partWrapper));

        // Headers
      
        

        // Wrap in HttpEntity
        HttpEntity<ContentRequest> httpEntity = new HttpEntity<>(request, headers);

        // Call API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);

        // Print response
        
        
        return response.getBody();

    }
    
}
