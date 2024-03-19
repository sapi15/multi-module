package com.multi.module.system.spring.config;

import com.multi.module.system.spring.interceptor.CommonInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
//@EnableTransactionManagement
//@EnableScheduling
//@ComponentScan(basePackages = "com.multi.module", excludeFilters={@ComponentScan.Filter(Repository.class)})
public class WebMvcConfig implements WebMvcConfigurer, WebMvcRegistrations {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/exclude");// ex
    }


}
