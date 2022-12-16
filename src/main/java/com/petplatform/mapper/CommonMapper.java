package com.petplatform.mapper;

import com.petplatform.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {

    void signUp(UserDto userDto);

    void addToken(String userId);

    int doubleCheck(String userId);

    void modifyPassword(UserDto userDto);

    void modifyUser(UserDto userDto);

    void deleteUser(UserDto userDto);

}
