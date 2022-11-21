package com.petplatform.mapper;

import com.petplatform.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {

    UserDto getUserInfo(String id);
    UserDto postUserInfo(UserDto userDto);

    UserDto signIn(UserDto userDto);

}
