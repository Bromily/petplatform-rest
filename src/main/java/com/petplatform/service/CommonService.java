package com.petplatform.service;

import com.petplatform.dto.UserDto;
import com.petplatform.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonService {

    @Autowired
    public CommonMapper mapper;

    @Transactional
    public UserDto getUserInfo(String id){
        System.out.println("찬익이 테스트용(get)");
        return mapper.getUserInfo(id);
    }

    @Transactional
    public UserDto postUserInfo(UserDto userDto){
        System.out.println("찬익이 테스트용(post)");
        return mapper.postUserInfo(userDto);
    }

}
