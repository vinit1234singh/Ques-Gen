package com.genques.genques.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genques.genques.config.ConfigDataSource;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class UploadAPIService {
     private static final String UPLOAD_DIR = "uploads";
     @Autowired
     GetParseData getParseData;
     @Autowired
     GeminiAPICall geminiAPICall;

    public String globalFileName = null;
    /*
     * Upload API service
     */
    public String requestFile(MultipartFile file, String descrition){
        DataSource dataSource = null;
        Connection connection = null;
        PreparedStatement preparedStatement=null;

         if(file.isEmpty()){
            return "File is Empty";
        }
        try {
            //int result =0;
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                
            }
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            dataSource = ConfigDataSource.source();
            connection = dataSource.getConnection();
            System.out.println("Connection Establish in upload API Service");
            preparedStatement = connection.prepareStatement("insert into filelstr (File_Name, File_Extension, File_Path, File_Size, File_Upload_Date) values (?,?,?,?,?)");
            preparedStatement.setString(1, file.getOriginalFilename());
            int index = 0;
            for(int i=0;i<file.getOriginalFilename().length();i++){
                if(file.getOriginalFilename().charAt(i) == '.'){
                    index = i;
                }
            }
            String extension = file.getOriginalFilename().substring(index+1, file.getOriginalFilename().length());
            preparedStatement.setString(2, extension);
            preparedStatement.setString(3, UPLOAD_DIR+"/"+file.getOriginalFilename());
            String size = Long.toString(file.getSize());
            preparedStatement.setString(4, size);
            preparedStatement.setDate(5, sqlDate);
           

            preparedStatement.executeUpdate();
            connection.close();
            System.out.println("data Updated");
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
            globalFileName = file.getOriginalFilename();
            System.out.println("File Uploaded:--"+file.getOriginalFilename());
            //String responseFileUpload = getParseData.getParseData();

            System.out.println("Response Generated from Python");

            log.info("Gemini call going to start");

            String geminiresponse = geminiAPICall.googleGemniAPICall(descrition);
            System.out.println(geminiresponse);
            return geminiresponse;
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public void storeFile(String globalFileName){
        this.globalFileName = globalFileName;
    }
    
    public String getFileName(){
        return globalFileName;
    }

}
