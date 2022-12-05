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

    public String signUp(UserDto userDto){
        try {
            SHA256 sha256 = new SHA256();
            userDto.setPassword(sha256.encrypt(userDto.getPassword()));
            mapper.signUp(userDto);
            return "가입되었습니다. 로그인 후 이용해 주세요.";
        }catch (Exception e){
            return "다시 시도해 주세요.";
        }
    }

    public int doubleCheck(String userId){
        return mapper.doubleCheck(userId);
    }

    public String modifyUser(UserDto userDto){
        try {
            if(userDto.getPassword()!=null){
                SHA256 sha256 = new SHA256();
                userDto.setPassword(sha256.encrypt(userDto.getPassword()));

                mapper.modifyPassword(userDto);
            }else {
                mapper.modifyUser(userDto);
            }
            return "success";

        }catch(Exception e){
            return "fail";
        }

    }

    public String deleteUser(UserDto userDto){

        try {
            mapper.deleteUser(userDto);

            return "success";
        }catch (Exception e){
            return "fail";
        }

    }

}
