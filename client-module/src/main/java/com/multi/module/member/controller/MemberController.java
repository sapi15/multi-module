package com.multi.module.member.controller;

import com.multi.module.member.entity.UserDto;
import com.multi.module.member.service.MemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/client/member")
public class MemberController {

    @Resource
    private MemberService memberService;


//    @GetMapping
//    public UserDto getUser(UserDto userDto){
//        log.info("Client Call User !!!");
//        userDto.setName("김길동");
//        userDto.setAge(40);
//        return memberService.getUser(userDto);
//    }

//    @GetMapping(value = "/test.do")
//    public String Test(){
//        return "client ok !!!";
//    }

    @GetMapping(value = "/test.do")
    public UserDto getUserData(UserDto userDto){
        userDto.setMemSeq(2);
        log.info("client getUserData !!!");
        return memberService.findUserData(userDto);
    }




}
