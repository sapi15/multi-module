package com.multi.module.config.security.service;

import com.multi.module.config.security.dao.JoinDao;
import com.multi.module.config.security.dto.JoinDto;
import com.multi.module.member.entity.UserDto;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService{

//    @Resource(name = JoinDao.BEAN_NAME)
    @Resource
    private JoinDao joinDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    private final JoinDao joinDao;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public JoinServiceImpl(JoinDao joinDao, BCryptPasswordEncoder bCryptPasswordEncoder){
//        this.joinDao = joinDao;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @Override
    public int joinProcess(JoinDto joinDto) {

        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        Boolean isExist = joinDao.existsByUsername(username);
        if(isExist){
            return 0;
        }

        UserDto userDto = new UserDto();
        userDto.setMemNm(username);
        userDto.setMemPwd(bCryptPasswordEncoder.encode(password));
        userDto.setMemAuth("ROLE_ADMIN");

        return joinDao.insertUser(userDto);
    }
}
