package com.multi.module.config.security.dao;

import com.multi.module.member.entity.UserDto;
import org.springframework.stereotype.Repository;

//@Repository(value = JoinDao.BEAN_NAME)
@Repository
public interface JoinDao {
    String BEAN_NAME = "joinDao";

    boolean existsByUsername(String username);

    int insertUser(UserDto userDto);

    UserDto findByUsername(String username);


}
