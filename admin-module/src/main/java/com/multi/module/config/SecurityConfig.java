package com.multi.module.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        //csrf disable
        httpSecurity.csrf((auth) -> auth.disable());            // JWT 방식에서는 필요 없는듯.

        //From 로그인 방식 disable
//        httpSecurity.formLogin((auth) -> auth.disable());       // JWT 방식에서는 필요 없는듯.

        //http basic 인증 방식 disable
//        httpSecurity.httpBasic((auth) -> auth.disable());       // JWT 방식에서는 필요 없는듯.

        httpSecurity
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login.do", "/loginProc").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER")
//                                            .requestMatchers("/", "/login.do", "/loginProc").permitAll()
//                                            .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated());

//        httpSecurity
//                .formLogin((auth) -> auth.loginPage("/login.do")
//                        .loginProcessingUrl("/loginProc").permitAll()
//                );



        return httpSecurity.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER\n" +
                "ROLE_MANAGER > ROLE_USER");

        return hierarchy;
    }
}
