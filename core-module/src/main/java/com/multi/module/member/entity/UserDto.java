package com.multi.module.member.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
//@AllArgsConstructor
//@Builder
@Table(name = "tb_member")
public class UserDto {

//    public UserDto(){}


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
