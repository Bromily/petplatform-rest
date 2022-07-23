package com.petplatform.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
