package com.genques.genques.service;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Service
@Component
@Slf4j

public class GetParseData {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.ocr.runtime.url}")
    private String parseUrl;

    @Autowired
    private UploadAPIService uploadAPIService;

    /**
     * @return Parsing data from OCR Microservice
     */
    public String getParseData() {
    log.info("Inside Parse Data");

    String fileName = uploadAPIService.getFileName();

    if (fileName == null || fileName.isEmpty()) {
        log.warn("No file name found in UploadAPIService");
        return "No file uploaded yet.";
    }

    String url = parseUrl + fileName;
    log.info("OCR Runtime URL fetched: {}", url);

    try {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();

        if (response.getStatusCode().is2xxSuccessful()) {
            if (responseBody == null || responseBody.isEmpty()) {
                log.info("Data not available");
                return "Data is Empty";
            }

            // Parse JSON and extract "text"
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            String textData = root.path("text").asText();

            log.info("Data Parse success.");
            return textData;

        } else if (response.getStatusCode().is4xxClientError()) {
            log.error("OCR API Client error: {}", response.getStatusCode().value());
            return "OCR API Client error: " + response.getStatusCode().value();
        } else if (response.getStatusCode().is5xxServerError()) {
            log.error("OCR API Server error");
            return "OCR API Server error";
        }

    } catch (Exception e) {
        log.error("Error while calling OCR API: ", e);
        return "Failed to parse data due to: " + e.getMessage();
    }

    return "Unexpected error";
}
}

