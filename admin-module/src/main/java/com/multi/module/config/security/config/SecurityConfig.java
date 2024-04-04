package com.multi.module.config.security.config;

import com.multi.module.config.security.jwt.JwtAuthFilter;
import com.multi.module.config.security.jwt.JwtUtil;
import com.multi.module.config.security.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

//    private static final String[] PERMIT_ALL_LIST ={
//            "/api/v1/**"
//    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf((csrf)->csrf.disable());
        httpSecurity.cors(Customizer.withDefaults());
        // formLogin, httpBasic 비활성화.
        httpSecurity.formLogin((auth)->auth.disable());
        httpSecurity.httpBasic((auth)->auth.disable());

        httpSecurity.authorizeHttpRequests((auth)->auth
                .requestMatchers(
                        new AntPathRequestMatcher("/"),
                        new AntPathRequestMatcher("/favicon.ico"),
                        new AntPathRequestMatcher("/api/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/user/**")).hasAnyRole("USER")
                .requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER")
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole("ADMIN")
                .anyRequest().authenticated());

        // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가.
        httpSecurity.addFilterBefore(new JwtAuthFilter(customUserDetailsService, jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // 세션 생성 및 사용 x
        httpSecurity.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return httpSecurity.build();
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public RoleHierarchy roleHierarchy() {

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER\n" +
                "ROLE_MANAGER > ROLE_USER");

        return hierarchy;
    }


}
