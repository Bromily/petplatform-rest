package com.petplatform.service;

import com.petplatform.common.SHA256;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    public CommonMapper mapper;

    public UserDto signIn(UserDto userDto) {

        SHA256 sha256 = new SHA256();

        try {
            UserDto userInfo = mapper.getUserInfo(userDto);

            if (userInfo.getPassword().equals(sha256.encrypt(userDto.getPassword()))) {
                System.out.println("success");
                return userInfo;
            } else {
                System.out.println("fail : check password!");
                return null;
            }
        }catch (Exception e){
            System.out.println("fail");
            return null;
        }

    }

}
