package com.multi.module.config.security.service;

import com.multi.module.config.security.dao.JoinDao;
import com.multi.module.config.security.dto.CustomUserDetails;
import com.multi.module.member.entity.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JoinDao joinDao;

    public CustomUserDetailsService(JoinDao joinDao) {

        this.joinDao = joinDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //DB에서 조회
        UserDto userData = joinDao.findByUsername(username);

        if (userData != null) {

            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
