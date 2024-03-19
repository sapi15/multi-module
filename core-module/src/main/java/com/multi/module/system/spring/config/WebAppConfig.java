package com.multi.module.system.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = DatabaseConfig.class)
//@Import(value = {DatabaseConfig.class, ResolverConfig.class})
@Configuration
public class WebAppConfig {

    public WebAppConfig(){
        System.out.println("WebAppConfig call!!!");
    }
}
