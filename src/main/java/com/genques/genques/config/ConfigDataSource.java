package com.genques.genques.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

public class ConfigDataSource {

    @Bean
    public static DataSource source(){

        DataSourceBuilder<?> dsb = DataSourceBuilder.create();
        dsb.driverClassName("com.mysql.cj.jdbc.Driver");
        dsb.url("jdbc:mysql://root:rIvYFPWTrILzAoUSNEqmGPEpnWOroLfc@mysql.railway.internal:3306/railway");
        dsb.username("root");
        dsb.password("rIvYFPWTrILzAoUSNEqmGPEpnWOroLfc");
        return dsb.build();
        
    }

}
