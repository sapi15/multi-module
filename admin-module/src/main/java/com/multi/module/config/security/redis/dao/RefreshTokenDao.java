package com.multi.module.config.security.redis.dao;

import com.multi.module.config.security.redis.dto.RefreshToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenDao {

    Optional<RefreshToken> findByAccessToken(String accessToken);

    int saveRefreshToken(RefreshToken refreshToken);

    int deleteRefreshToken(RefreshToken refreshToken);


}
