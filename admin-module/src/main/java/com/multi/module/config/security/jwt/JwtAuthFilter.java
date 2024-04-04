package com.multi.module.config.security.jwt;

import com.multi.module.config.security.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * OncePerRequestFilter -> 한 번 실행
 */
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;


    /**
     * 토큰 검증 수행.
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        // JWT가 헤더에 있는 경우.
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            // JWT 유효성 검증
            if(jwtUtil.validateToken(token)){
                String userId = jwtUtil.getUserId(token);

                // 유저와 토큰 일치 시 userDetails 생성.
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);

                if(userDetails != null){
                    // UserDetails, password, role -> 접근 권한 인증 token 생성.
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // 현재 Request의 security context에 접근권한 설정.
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        // 다음 필터로 넘기기.
        filterChain.doFilter(request, response);
    }
}
