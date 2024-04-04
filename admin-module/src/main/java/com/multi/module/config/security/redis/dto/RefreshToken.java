package com.multi.module.config.security.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@RedisHash(value = "jwtToken", timeToLive = 60*60*24*3)     // 유효시간 3일
public class RefreshToken {

    @Id
    private String memNm;

    private String refreshToken;

    @Indexed                            // 해당 필드 값으로 데이터를 찾아올수 있게 함.
    private String accessToken;
}
