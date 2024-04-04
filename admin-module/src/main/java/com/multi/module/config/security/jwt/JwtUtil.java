package com.multi.module.config.security.jwt;

import com.multi.module.config.security.dto.CustomUserInfoDto;
import com.multi.module.member.entity.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.ZonedDateTime;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final long accessTokenExpTime;


    public JwtUtil(@Value("${jwt.secret}") String secretKey,
                   @Value("${jwt.expiration_time}") long accessTokenExpTime){
        byte[] byteSecretKey = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(byteSecretKey);
        this.accessTokenExpTime =accessTokenExpTime;
    }


    /**
     * Access Token 생성
     * @param customUserInfoDto
     * @return
     */
    public String createAccessToken(CustomUserInfoDto customUserInfoDto){
        return createToken(customUserInfoDto, accessTokenExpTime);
    }


    /**
     * JWT 생성
     * @param customUserInfoDto
     * @param expireTime
     * @return
     */
    private String createToken(CustomUserInfoDto customUserInfoDto, long expireTime){
        Claims claims = Jwts.claims();
        claims.put("memNm", customUserInfoDto.getMemNm());
        claims.put("memEmail", customUserInfoDto.getMemEmail());
        claims.put("memAuth", customUserInfoDto.getMemAuth());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);
        
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Token에서 User ID 추출.
     * @param token
     * @return
     */
    public String getUserId(String token){
        return parseClaims(token).get("memNm", String.class);
    }

    public boolean validateToken(String token){
        try{
          Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
          return true;
        }catch (SecurityException | MalformedJwtException e){
            log.info("Invalid JWT Token", e);
        }catch (ExpiredJwtException e){
            log.info("Expired JWT Token", e);
        }catch (UnsupportedJwtException e){
            log.info("Unsupported JWT Token", e);
        }catch (IllegalArgumentException e){
            log.info("JWT claims string is empty", e);
        }
        return false;
    }


    /**
     * JWT Claims 추출.
     * @param accessToken
     * @return
     */
    public Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    /**
     * refresh token 생성
     * @return
     */
    public String createRefreshToken(){
        Claims claims = Jwts.claims();

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3))//유효시간 (3일)
                .signWith(SignatureAlgorithm.HS256, this.key) //HS256알고리즘으로 key를 암호화 해줄것이다.
                .compact();
    }

}
