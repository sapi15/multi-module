package com.multi.module;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "com.multi.module")
@Slf4j
//@EnableTransactionManagement
@ComponentScan(basePackages = {"com.multi.module"})
//@MapperScan(basePackages = {"com.multi.module.member.repository"})
public class AdminApplication {

    public static void main(String[] args){
        log.info("AdminApplication RUN !!!");
        SpringApplication.run(AdminApplication.class, args);
    }

}
