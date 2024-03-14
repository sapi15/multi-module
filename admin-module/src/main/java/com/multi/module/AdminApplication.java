package com.multi.module;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Slf4j
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.multi.module")
@EnableTransactionManagement
//@ComponentScan(basePackages = {"com.multi.module"})
//@MapperScan(basePackages = {"com.multi.module"})
public class AdminApplication {

    public static void main(String[] args){
        log.info("AdminApplication RUN !!!");
        SpringApplication.run(AdminApplication.class, args);
    }

//    @Bean
//    public InternalResourceViewResolver setupViewResolver(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }

}
