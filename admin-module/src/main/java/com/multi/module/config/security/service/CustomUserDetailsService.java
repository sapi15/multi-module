package com.multi.module.config.security.service;

import com.multi.module.config.security.dao.MemberDao;
import com.multi.module.config.security.dto.CustomUserDetails;
import com.multi.module.config.security.dto.CustomUserInfoDto;
import com.multi.module.config.security.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberDao memberDao;
    private final ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberDto memberDto = memberDao.findById(id);

        if(memberDto == null){
            log.info("해당하는 유저가 없습니다.");
            throw new UsernameNotFoundException("해당하는 유저가 없습니다.");
        }

        CustomUserInfoDto customUserInfoDto = modelMapper.map(memberDto, CustomUserInfoDto.class);

        return new CustomUserDetails(customUserInfoDto);
    }
}
