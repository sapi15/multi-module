package com.multi.module;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Log4j2
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
