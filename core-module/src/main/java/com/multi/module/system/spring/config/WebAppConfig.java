package com.multi.module.system.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = DatabaseConfig.class)
@Configuration
public class WebAppConfig {

    public WebAppConfig(){
        System.out.println("WebAppConfig call!!!");
    }
}
