package com.petplatform.service;

import com.petplatform.common.SHA256;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.CommonMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class CommonService {

    @Autowired
    public CommonMapper mapper;

    public UserDto signIn(UserDto userDto) throws NoSuchAlgorithmException {

        SHA256 sha256 = new SHA256();

        try {
            UserDto userInfo = mapper.getUserInfo(userDto);

            if (userInfo.getPassword().equals(sha256.encrypt(userDto.getPassword()))) {
                return userInfo;
            } else {
                return new UserDto();
            }
        }catch (Exception e){
            return new UserDto();
        }

    }

}
