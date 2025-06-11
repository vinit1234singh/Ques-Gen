package com.genques.genques.service;


import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Service
@Component
@Slf4j
public class GetParseData {

    private final RestTemplate restTemplate = new RestTemplate();

private static final Logger logger = LoggerFactory.getLogger(GetParseData.class);
    String parseData = null;

    /**
     * @return Parsing data from OCR Microservice
     */
    public String getParseData(){
            logger.info("Inside Parse Data");
            String url = "https://1e8d-35-247-53-54.ngrok-free.app/getParsedata";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            parseData = response.getBody();
            if(parseData == "" || parseData == null){
                logger.info("Data not available");
                return "Data is Empty";
            }
            else if(response.getStatusCode().is4xxClientError()){
                logger.info("OCR API Server error code"+Integer.toString(response.getStatusCode().value()));
                return "OCR API Server error code"+Integer.toString(response.getStatusCode().value());
            }
            else if (response.getStatusCode().is5xxServerError()) {
                logger.info("Data Parse Internal server error");
                return "OCR API Server Side issue";
                
            }
            System.out.println(parseData);
            logger.info("Data Parse success.....");
            logger.info("Parse Data EXIT");
            return response.getBody();
            
    }
}
