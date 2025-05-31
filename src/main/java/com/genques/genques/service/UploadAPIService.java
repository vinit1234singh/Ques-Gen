package com.genques.genques.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.genques.genques.config.ConfigDataSource;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UploadAPIService {
     private static final String UPLOAD_DIR = "uploads";
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
            preparedStatement = connection.prepareStatement("insert into filelstr (File_Name, File_Extension, File_Path, File_Size, File_Upload_Date) values (?,?,?,?,?)");

            preparedStatement.setString(1, file.getOriginalFilename());
            System.out.println("data Updated");
            preparedStatement.setString(2, "jpg");
            System.out.println("data Updated");
            preparedStatement.setString(3, UPLOAD_DIR+"/"+file.getOriginalFilename());
            System.out.println("data Updated");
            String size = Long.toString(file.getSize());
            preparedStatement.setString(4, size);
            System.out.println("data Updated");
            preparedStatement.setDate(5, sqlDate);
            System.out.println("data Updated");

            preparedStatement.executeUpdate();
            connection.close();
            System.out.println("data Updated");
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
            return "File Uploaded:--"+file.getOriginalFilename();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

}
