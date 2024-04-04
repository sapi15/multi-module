package com.multi.module.config.security.service;

import com.multi.module.config.security.dao.MemberDao;
import com.multi.module.config.security.dto.CustomUserInfoDto;
import com.multi.module.config.security.dto.LoginRequestDto;
import com.multi.module.config.security.dto.MemberDto;
import com.multi.module.config.security.jwt.JwtUtil;
import com.multi.module.config.security.redis.dao.RefreshTokenDao;
import com.multi.module.config.security.redis.dto.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthSerciveImpl implements AuthService{

    private final JwtUtil jwtUtil;
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RefreshTokenDao refreshTokenDao;


    @Override
    public String login(LoginRequestDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        MemberDto memberDto = memberDao.findMemberByEmail(email);
        if(memberDto == null){
            log.info("이메일 존재하지 않습니다.");
            throw new UsernameNotFoundException("이메일 존재하지 않습니다.");
        }

        // 암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null
        if(!passwordEncoder.matches(password, memberDto.getMemPwd())){
            log.info("비밀번호가 일치하지 않습니다.");
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        CustomUserInfoDto customUserInfoDto = modelMapper.map(memberDto, CustomUserInfoDto.class);

        String accessToken = jwtUtil.createAccessToken(customUserInfoDto);               // JWT create.



//        String refreshToken = jwtUtil.createRefreshToken();
//        refreshTokenDao.saveRefreshToken(new RefreshToken(dto.getId(), refreshToken, accessToken));


        return accessToken;
    }

}
