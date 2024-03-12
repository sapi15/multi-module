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
@RequestMapping(value = "/admin/member")
public class MemberController {

    @Resource
    private MemberService memberService;


//    @GetMapping
//    public UserDto getUser(UserDto userDto){
//        log.info("Admin call User !!!");
//        userDto.setName("김철수");
//        userDto.setAge(35);
//        return memberService.getUser(userDto);
//
//    }

//    @GetMapping(value = "/test.do")
//    public String Test(){
//        return "admin ok !!!";
//    }

    @GetMapping(value = "/test.do")
    public UserDto getUserData(UserDto userDto){
        userDto.setMemSeq(1);
        log.info("admin getUserData !!!");
        return memberService.findUserData(userDto);
    }

}
