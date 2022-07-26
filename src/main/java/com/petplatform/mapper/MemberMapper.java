package com.petplatform.mapper;

import com.petplatform.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface MemberMapper {

    MemberDto selectMember(MemberDto info);

    void insertMember(MemberDto info);
}
