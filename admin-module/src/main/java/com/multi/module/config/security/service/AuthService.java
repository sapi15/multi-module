package com.multi.module.config.security.service;

import com.multi.module.config.security.dto.LoginRequestDto;

public interface AuthService {

    String login(LoginRequestDto dto);
}
