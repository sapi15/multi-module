package com.multi.module.member.repository;

import com.multi.module.member.entity.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    String BEAN_NAME = "userDao";

    UserDto selectUserData(UserDto userDto);




}
