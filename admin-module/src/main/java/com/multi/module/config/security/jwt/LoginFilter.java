//package com.multi.module.config.security.jwt;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//public class LoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//
//    private  final JwtUtil jwtUtil;
//
//    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//        setFilterProcessesUrl("/login");        // login url setting
//    }
//
//
//
//
//}
