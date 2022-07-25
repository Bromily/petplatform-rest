package com.petplatform.mapper;

import com.petplatform.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface MemberMapper {

    public MemberDto selectMember(String id, String pw);

    public void insertMember(String id, String pw, String name, String sex);
}
