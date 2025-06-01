package com.genques.genques.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Component
public class GetParseData {

    private final RestTemplate restTemplate = new RestTemplate();

    String parseData = null;

    /**
     * @return Parsing data from OCR Microservice
     */
    public String getParseData(){
       
            String url = "https://aeab-35-226-2-39.ngrok-free.app/getParsedata";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            parseData = response.getBody();
            System.out.println(parseData);
            return response.getBody();
    }
}
