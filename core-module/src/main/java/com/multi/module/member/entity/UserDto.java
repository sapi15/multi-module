package com.multi.module.member.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
//@AllArgsConstructor
//@Builder
//@Table(name = "tb_member")
public class UserDto {

//    public UserDto(){}

    private Integer memSeq;

    private String memNm;

    private String memAuth;

    private String memPwd;

//    @Id
//    @Column(name = "MEM_SEQ")
//    private Integer memSeq;
//
//    @Column(name = "MEM_ID")
//    private String memId;
//
//    @Column(name = "MEM_AUTH")
//    private String memAuth;
//
//    @Column(name = "MEM_PWD")
//    private String memPwd;




}
