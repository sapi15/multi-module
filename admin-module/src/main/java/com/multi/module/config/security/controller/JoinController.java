package com.multi.module.config.security.controller;

import com.multi.module.config.security.dto.JoinDto;
import com.multi.module.config.security.dto.MemberDto;
import com.multi.module.config.security.service.JoinService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class JoinController {

    @Resource
    private JoinService joinService;


    @PostMapping("/join")
    public String joinProcess(JoinDto joinDto) {
        String result = "Fail";
        int cnt = joinService.joinProcess(joinDto);
        if(cnt == 1){
            result = "Ok";
        }

        return result;
    }
}
