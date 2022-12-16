package com.petplatform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@Schema
public class UserDto {

    @Schema(description = "아이디")
    private String userId;
    @Schema(description = "비밀번호")
    private String password;
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "생년월일")
    private String birth;
    @Schema(description = "주소")
    private String address;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "휴대폰번호")
    private String phoneNumber;
    @Schema(description = "유저타입")
    private String userType;
    @Schema(description = "사용여부")
    private String useYn;
    @Schema(hidden = true)
    private Collection<? extends GrantedAuthority> authorities;

}
