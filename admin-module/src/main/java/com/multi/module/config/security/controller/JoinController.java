package com.multi.module.config.security.controller;

import com.multi.module.config.security.dto.JoinDto;
import com.multi.module.config.security.service.JoinService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {

    @Resource
    private JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(JoinDto joinDto) {
        String result = "Fail";

        System.out.println(joinDto.getUsername());
        int cnt = joinService.joinProcess(joinDto);
        if(cnt == 1){
            result = "Ok";
        }

        return result;
    }
}
