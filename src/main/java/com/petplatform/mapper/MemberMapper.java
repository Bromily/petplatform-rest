package com.petplatform.mapper;

import com.petplatform.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberMapper {

    public MemberDto selectMember(String id);

    public void insertMember(String id, String pw, String name, String sex);

}
