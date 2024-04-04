package com.multi.module.config.security.controller;

import com.multi.module.config.security.dto.LoginRequestDto;
import com.multi.module.config.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AuthApiController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<String> getMemberProfile(@RequestBody LoginRequestDto loginRequestDto){
        String token = authService.login(loginRequestDto);


        return ResponseEntity.status(HttpStatus.OK)
                .body("Bearer " + token);
    }


}
