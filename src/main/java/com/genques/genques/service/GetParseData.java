package com.genques.genques.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetParseData {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * @return Parsing data from OCR Microservice
     */
    public String getParseData(){
       
            String url = "https://9034-35-229-129-145.ngrok-free.app";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();


       
    }

}
