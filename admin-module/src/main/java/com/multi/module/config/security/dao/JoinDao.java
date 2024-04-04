package com.multi.module.config.security.dao;

import com.multi.module.config.security.dto.JoinDto;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinDao {

    int saveMember(JoinDto joinDto);

    boolean existsByUsername(String id);

    JoinDto findByUsername(String id);



}
