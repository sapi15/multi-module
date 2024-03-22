package com.multi.module.member.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
//@AllArgsConstructor
@Builder
@Table(name = "tb_member")
public class UserDto {

    @Id
    @Column(name = "MEM_SEQ")
    private Integer memSeq;

    @Column(name = "MEM_ID")
    private String memId;

    @Column(name = "MEM_AUTH")
    private String memAuth;

    @Column(name = "MEM_PWD")
    private String memPwd;




}
