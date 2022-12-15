package com.petplatform.service;

import com.petplatform.common.SHA256;
import com.petplatform.controller.TokenController;
import com.petplatform.dto.ResponseDto;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.CommonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonService {

    @Autowired
    public CommonMapper mapper;

    public ResponseDto signUp(UserDto userDto){
        ResponseDto response = new ResponseDto();
        try {
            SHA256 sha256 = new SHA256();
            userDto.setPassword(sha256.encrypt(userDto.getPassword()));
            mapper.signUp(userDto);
            response.setBody("가입되었습니다. 로그인 후 이용해 주세요.");

            return response;
        }catch (Exception e){
            response.setBody("다시 이용해주세요.");
            return response;
        }
    }

    public ResponseDto doubleCheck(String userId){
        ResponseDto response = new ResponseDto();
        if(mapper.doubleCheck(userId)<1){
            response.setBody("사용가능한 ID입니다.");
        }else {
            response.setBody("이미 사용하고 있는 아이디가 있습니다.");
        }

        return response;
    }

    public String modifyUser(UserDto userDto){
        try {
            if(!userDto.getPassword().equals("")){
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
