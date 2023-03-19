package com.petplatform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String userId;
    private String password;
    private String name;
    private String email;
    private String birth;
    private String sns;
    private String reg_date;

//    private String userId;
//    private String password;
//    private String nickName;
//    private String name;
//    private String birth;
//    private String address;
//    private String email;
//    private String phoneNumber;
//    private String userType;
//    private String useYn;

}
