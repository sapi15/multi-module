package com.multi.module.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JWT 발급.
 */
@Component
public class JWTUtil {

    private Key key;


    public JWTUtil(@Value("${jwt.secret}")String secret) {
        byte[] byteSecretKey = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(byteSecretKey);

        System.out.println("key->"+key);
    }

    public String getUsername(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("memNm", String.class);
    }

    public String getRole(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("memAuth", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public String createJwt(String username, String role, Long expiredMs) {

        Claims claims = Jwts.claims();
        claims.put("memNm", username);
        claims.put("memAuth", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }



//    private SecretKey secretKey;
//
//    /**
//     * application.properties에 설정한 암호화 키를 jwt에서 사용할수 있게 객체화?
//     * @param secret
//     */
//    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
//
//        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
//    }
//
//    /**
//     * 검증 로직
//     */
//    public String getUsername(String token) {
//
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
//    }
//
//    public String getRole(String token) {
//
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("password", String.class);
//    }
//
//    public Boolean isExpired(String token) {
//
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
//    }
//    /**
//     * 검증 로직 end
//     */
//
//
//    /**
//     * 로그인 성공시 토큰 생성 메소드.
//     * @param username
//     * @param role
//     * @param expiredMs
//     * @return
//     */
//    public String createJwt(String username, String role, Long expiredMs) {
//
//        return Jwts.builder()
//                .claim("username", username)                                //키 set?
//                .claim("role", role)
//                .issuedAt(new Date(System.currentTimeMillis()))                     // 토큰 발생 시간 set
//                .expiration(new Date(System.currentTimeMillis() + expiredMs))       // 토큰 소멸 날짜 set
//                .signWith(secretKey)                                                // 암호화 진행.
//                .compact();                                                         // 데이터 직렬화 (토큰 발행)
//    }




}
