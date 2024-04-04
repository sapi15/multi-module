package com.multi.module.config.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private String id;
    private String password;
    private String email;
}
