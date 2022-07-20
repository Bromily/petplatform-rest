package com.petplatform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private String id;
    private String pw;
    private String name;
    private String sex;

    public MemberDto(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public MemberDto(String id, String pw, String name, String sex) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.sex = sex;
    }
}
