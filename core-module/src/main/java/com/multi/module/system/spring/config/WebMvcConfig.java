package com.multi.module.system.spring.config;

import com.multi.module.system.spring.interceptor.CommonInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Log4j2
@Configuration
@ComponentScan("com.multi.module")
//@EnableWebMvc
//@EnableTransactionManagement
//@EnableScheduling
//@ComponentScan(basePackages = "com.multi.module", excludeFilters={@ComponentScan.Filter(Repository.class)})
public class WebMvcConfig implements WebMvcConfigurer, WebMvcRegistrations {
//public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/exclude");// ex
    }

//    /**
//     * 다국어 지원 방법 1
//     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        CustomLocaleResolver customLocaleResolver = new CustomLocaleResolver();
//        customLocaleResolver.setDefaultLocale(Locale.KOREA);
//        return customLocaleResolver;
//    }
//
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }
//
//
//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setCacheSeconds(60);
//        messageSource.setUseCodeAsDefaultMessage(true);
//        return messageSource;
//    }
//
//    @Bean(name = "messageSourceAccessor")
//    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
//        return new MessageSourceAccessor(messageSource, Locale.getDefault());
//    }
//
//
//    public static class CustomLocaleResolver extends AcceptHeaderLocaleResolver {
//
//        String[] mLanguageCode = new String[]{"ko", "en"};
//        List<Locale> mLocales = Arrays.asList(new Locale("en"), new Locale("es"), new Locale("ko"));
//
//        @Override
//        public Locale resolveLocale(HttpServletRequest request) {
//            // 언어팩 변경
//            String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
//            if (acceptLanguage == null || "".equals(acceptLanguage)) {
//                return Locale.getDefault();
//            }
//            List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
//
//            mLocales = new ArrayList<>();
//            for (String code : mLanguageCode) {
//                mLocales.add(new Locale(code));
//            }
//            return Locale.lookup(list, mLocales);
//        }
//    }
//    /**
//     * 다국어 지원 방법 1 end
//     */



}
