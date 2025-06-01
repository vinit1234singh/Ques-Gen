package com.genques.genques.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.genques.genques.service.UploadAPIService;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UploadAPIService uploadAPIService;

    @GetMapping("/user")
    private String getUser(){
        return "USer";
    }
    //private static final String UPLOAD_DIR = "uploads";
    @PostMapping("/uploads")
    private ResponseEntity<String> uploadFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam("description") String description
    ){
        String res=uploadAPIService.requestFile(file, description);
        return ResponseEntity.ok(res);

    }

}
