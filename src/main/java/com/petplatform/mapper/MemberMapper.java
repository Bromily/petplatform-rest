package com.petplatform.mapper;

import com.petplatform.dto.MemberDto;
import com.petplatform.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDto selectMember(MemberDto info); // string id

    void insertMember(UserDto info);
}

