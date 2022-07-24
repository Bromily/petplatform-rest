package com.petplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String id;
    private String pw;
    private String name;
    private String sex;

    public MemberDto(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

}
