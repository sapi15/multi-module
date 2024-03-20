//package com.multi.module.system.spring.config;
//
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.MessageSourceAccessor;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//
//import java.io.IOException;
//
////@Configuration
//public class MessageConfig {
//
//    @Bean
//    public MessageSource messageSource() throws IOException{
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("classpath:/messages/");
//        messageSource.setDefaultEncoding("UTF-8");
//        // locale에 해당하는 file이 없을 경우 system locale을 사용
//        messageSource.setFallbackToSystemLocale(false);
//
//        return messageSource;
//    }
//
//    @Bean
//    public MessageSourceAccessor messageSourceAccessor() throws IOException {
//        return new MessageSourceAccessor(messageSource());
//    }
//
//
//}
