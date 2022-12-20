package com.petplatform.service;

import com.petplatform.common.SHA256;
import com.petplatform.dto.ResponseDto;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.CommonMapper;
import com.petplatform.security.TokenCreater;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class CommonService {

    @Autowired
    public CommonMapper mapper;

    @Autowired
    private TokenCreater tokenCreater;

    public ResponseDto signIn(UserDto user,
                              HttpServletRequest servletRequest,
                              HttpServletResponse servletResponse) {
        ResponseDto response = new ResponseDto();

        try {
            SHA256 sha256 = new SHA256();
            UserDto tokenUser = mapper.getUserInfo(user);
            if (tokenUser.getPassword().equals(sha256.encrypt(user.getPassword()))) {
                tokenUser.setPassword("");
                tokenCreater.tokenCreate(tokenUser, servletRequest, servletResponse);
                response = new ResponseDto(tokenUser, null);
            } else {
                response.setBody("ID 또는 비밀번호를 확인해 주세요.");
            }
        }catch(Exception e){
            response = new ResponseDto(null, e);
        }

        return response;
    }

    public ResponseDto signUp(UserDto userDto){
        ResponseDto response = new ResponseDto();
        try {
            SHA256 sha256 = new SHA256();
            userDto.setPassword(sha256.encrypt(userDto.getPassword()));
            mapper.signUp(userDto);
            mapper.addToken(userDto.getUserId());
            response.setBody("가입되었습니다. 로그인 후 이용해 주세요.");

            return response;
        }catch (Exception e){
            response.setBody("다시 이용해주세요.");
            return response;
        }
    }

    public ResponseDto doubleCheck(String userId){
        ResponseDto response = new ResponseDto();
        try {
            response.setBody(mapper.doubleCheck(userId));
        }catch (Exception e){
            response.setBody(9999);
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
