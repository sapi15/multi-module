package com.multi.module.member.service;

import com.multi.module.member.entity.UserDto;
import com.multi.module.member.repository.UserDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
    public static final String BEAN_NAME = "memberService";

    @Resource
    private UserDao userDao;


//    @Override
//    public UserDto getUser(UserDto userDto) {
//        log.debug("UserDto !!!");
//        return UserDto.builder().name(userDto.getName()).age(userDto.getAge()).build();
//    }

    @Override
    public UserDto findUserData(UserDto userDto) {
        return userDao.selectUserData(userDto);
    }

}
