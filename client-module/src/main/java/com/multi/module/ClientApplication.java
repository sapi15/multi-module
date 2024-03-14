package com.multi.module;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.multi.module")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.multi.module"})
@MapperScan(basePackages = {"com.multi.module"})
public class ClientApplication {

    public static void main(String[] args){
        log.info("ClientApplication RUN !!!");
        SpringApplication.run(ClientApplication.class, args);
    }
}
