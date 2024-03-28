package com.multi.module.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.module.config.security.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 로그인 처리
 * 
 * UsernamePasswordAuthenticationFilter : Form based Authentication 방식으로 인증을 진행할 때 아이디, 패스워드 데이터를 파싱하여 인증 요청을 위임하는 필터.
 * 즉, 유저가 로그인 창에서 Login을 시도할 때 보내지는 요청에서 아이디(username)와 패스워드(password) 데이터를 가져온 후 인증을 위한 토큰을 생성 후 인증을 다른 쪽에 위임하는 역할을 하는 필터.
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    //JWTUtil 주입
    private  final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/login.do");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println("username-> "+username);
        System.out.println("password-> "+password);

        UsernamePasswordAuthenticationToken authToken = null;
        try{
            //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
            authToken = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            System.out.println("authToken -> " + authToken);
        }catch (Exception e){
            System.out.println("e->"+e.getMessage());
            throw new RuntimeException(e);
        }

        // TODO: 여기서 에러나는 듯..
        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        //UserDetailsS (유저 정보)
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        // role 값 get
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(username, role, 60*60*10L);

        response.addHeader("Authorization", "Bearer " + token);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        System.out.println("401 !!!");

        //로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
    }



}
