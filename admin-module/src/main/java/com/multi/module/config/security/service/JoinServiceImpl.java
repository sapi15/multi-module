package com.multi.module.config.security.service;

import com.multi.module.config.security.dao.JoinDao;
import com.multi.module.config.security.dto.JoinDto;
import com.multi.module.member.entity.UserDto;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService{

    @Resource
    private JoinDao joinDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public int joinProcess(JoinDto joinDto) {

        String id = joinDto.getMemNm();
        String password = joinDto.getMemPwd();

        Boolean isExist = joinDao.existsByUsername(id);
        if(isExist){
            return 0;
        }

        joinDto.setMemPwd(bCryptPasswordEncoder.encode(password));
        joinDto.setMemAuth("ADMIN");

        return joinDao.saveMember(joinDto);
    }
}
