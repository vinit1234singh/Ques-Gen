package com.genques.genques.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class GetParseData {

    private final RestTemplate restTemplate = new RestTemplate();

    String parseData = null;

    /**
     * @return Parsing data from OCR Microservice
     */
    public String getParseData(){
       
            String url = "https://1e8d-35-247-53-54.ngrok-free.app/getParsedata";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            parseData = response.getBody();
            if(parseData == "" || parseData == null){
                log.debug("Data not available");
                return "Data is Empty";
            }
            else if(response.getStatusCode().is4xxClientError()){
                log.debug("OCR API Server error code"+Integer.toString(response.getStatusCode().value()));
                return "OCR API Server error code"+Integer.toString(response.getStatusCode().value());
            }
            else if (response.getStatusCode().is5xxServerError()) {
                log.debug("Data Parse Internal server error");
                return "OCR API Server Side issue";
                
            }
            System.out.println(parseData);
            log.debug("Data Parse success.....");
            return response.getBody();
            
    }
}
