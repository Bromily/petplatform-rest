package com.petplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private String userid;
    private String password;
    private String name;
    private String birth;
    private String address;
    private String phone_number;
    private String user_type;

    public MemberDto(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
    public MemberDto(String userid, String password, String name, String birth, String address, String phone_number, String user_type) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.phone_number = phone_number;
        this.user_type = user_type;
    }
}
