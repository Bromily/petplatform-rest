package com.petplatform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto {

    private String userId;
    private String password;
    private String nickName;
    private String name;
    private String birth;
    private String address;
    private String email;
    private String phoneNumber;
    private String userType;
    private String useYn;

    private String token;
    Collection<? extends GrantedAuthority> authorities;

}
