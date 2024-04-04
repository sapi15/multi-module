package com.multi.module.config.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final CustomUserInfoDto customUserInfoDto;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_"+customUserInfoDto.getMemAuth());

        System.out.println("roles->"+roles);

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

//        Collection<GrantedAuthority> collection = new ArrayList<>();
//        collection.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//
//                String roles = "ROLE_" + customUserInfoDto.getMemAuth();
//
//                System.out.println("roles->"+roles);
//
//                return roles;
//            }
//        });
//
//        System.out.println("collection->"+collection);
//
//        return collection;
    }

    @Override
    public String getPassword() {
        return customUserInfoDto.getMemPwd();
    }

    @Override
    public String getUsername() {
        return customUserInfoDto.getMemNm();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
