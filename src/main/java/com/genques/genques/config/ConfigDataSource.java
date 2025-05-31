package com.genques.genques.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

public class ConfigDataSource {

    @Bean
    public static DataSource source(){

        DataSourceBuilder<?> dsb = DataSourceBuilder.create();
        dsb.driverClassName("com.mysql.cj.jdbc.Driver");
        dsb.url("jdbc:mysql://localhost:3306/uploadapi");
        dsb.username("root");
        dsb.password("");
        System.out.println("Connection Establish");
        return dsb.build();
        
    }

}
