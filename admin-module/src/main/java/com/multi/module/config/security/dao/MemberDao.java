package com.multi.module.config.security.dao;

import com.multi.module.config.security.dto.MemberDto;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

    MemberDto findMemberByEmail(String email);

    MemberDto findById(String id);



}
