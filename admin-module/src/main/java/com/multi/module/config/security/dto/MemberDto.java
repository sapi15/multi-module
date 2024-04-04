package com.multi.module.config.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private Integer memSeq;

    private String memNm;

    private String memAuth;

    private String memPwd;

    private String memEmail;


}
