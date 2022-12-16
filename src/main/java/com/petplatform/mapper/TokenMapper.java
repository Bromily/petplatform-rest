package com.petplatform.mapper;

import com.petplatform.dto.RefreshTokenDto;
import com.petplatform.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {

    UserDto getUserById(String userId);
    RefreshTokenDto getRefreshToken(String userId);
    void updateRefreshToken(RefreshTokenDto tokenDto);

    void deleteRefreshToken(String refreshToken);

}
