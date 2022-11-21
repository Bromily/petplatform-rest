package com.petplatform.dto;

import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String password;
    private String name;
    private String birth;
    private String address;
    private String phoneNumber;
    private String userType;

}
