package com.petplatform.service;

import com.petplatform.common.SHA256;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Service
public class CommonService {

    @Autowired
    public CommonMapper mapper;

    public UserDto getUserInfo(String id){
        System.out.println("찬익이 테스트용(get)");
        System.out.println(mapper.getUserInfo(id).getName());
        return mapper.getUserInfo(id);
    }

    public UserDto postUserInfo(UserDto userDto){
        System.out.println("찬익이 테스트용(post)");
        return mapper.postUserInfo(userDto);
    }

    public UserDto signIn(UserDto userDto, HttpSession session) throws NoSuchAlgorithmException {

        SHA256 sha256 = new SHA256();

        UserDto userInfo = mapper.postUserInfo(userDto);

        if(userInfo.getPassword().equals(sha256.encrypt(userDto.getPassword()))) {

            session.setAttribute("userInfo", userInfo);
            session.setMaxInactiveInterval(3600);

            System.out.println(userInfo.getName());

            return userInfo;

        }else return null;

    }

    public UserDto getSession(HttpSession session){
        UserDto result = (UserDto) session.getAttribute("userInfo");

        return result;
    }

}
