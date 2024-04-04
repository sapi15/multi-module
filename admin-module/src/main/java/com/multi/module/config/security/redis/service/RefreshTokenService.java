package com.multi.module.config.security.redis.service;

import com.multi.module.config.security.redis.dao.RefreshTokenDao;
import com.multi.module.config.security.redis.dto.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenDao refreshTokenDao;


    @Transactional
    public void saveTokenInfo(String employeeId, String refreshToken, String accessToken) {
        refreshTokenDao.saveRefreshToken(new RefreshToken(employeeId, refreshToken, accessToken));
    }

    @Transactional
    public void removeRefreshToken(String accessToken) {
        refreshTokenDao.findByAccessToken(accessToken)
                .ifPresent(refreshToken -> refreshTokenDao.deleteRefreshToken(refreshToken)); // findByAccessToken 결과 값이 있으면, deleteRefreshToken 실행
    }
}
