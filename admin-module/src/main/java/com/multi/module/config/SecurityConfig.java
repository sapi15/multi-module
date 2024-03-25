package com.multi.module.config;

import com.multi.module.config.security.jwt.JWTFilter;
import com.multi.module.config.security.jwt.JWTUtil;
import com.multi.module.config.security.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;

    //JWTUtil 주입
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        //csrf disable, REST API이므로 csrf 보안을 사용하지 않음
        httpSecurity.csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        httpSecurity.formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable, REST API이므로 basic auth 보안을 사용하지 않음
        httpSecurity.httpBasic((auth) -> auth.disable());

        httpSecurity.cors((auth) -> auth.disable());

//        httpSecurity.headers((headerConfig) -> headerConfig
//                .frameOptions((frameOptionsConfig) -> frameOptionsConfig.disable()));

        httpSecurity
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/favicon.ico", "/login", "/join").permitAll()
//                        .requestMatchers("/admin/login/**").permitAll()
//                                            .requestMatchers("/", "/login.do", "/loginProc").permitAll()
                                            .requestMatchers("/user/**").hasAnyRole("USER")
                                            .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                            .requestMatchers("/manager/**").hasAnyRole("MANAGER")
                                            .anyRequest().authenticated());

        // JWT를 사용하기 때문에 세션을 사용하지 않음
        httpSecurity.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // LoginFilter 앞에 JWTFilter 주입. JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
        httpSecurity.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        httpSecurity.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

//        httpSecurity
//                .formLogin((auth) -> auth
//                                    .loginPage("/admin/login/page.do")
//                                    .usernameParameter("username")
//                                    .passwordParameter("password")
//                                    .defaultSuccessUrl("/", true)
//                                    .loginProcessingUrl("/admin/loginProc.do").permitAll()
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
