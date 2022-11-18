package com.petplatform.mapper;

import com.petplatform.controller.MemberController;
import com.petplatform.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Mapper
public interface MemberMapper {

    MemberDto selectMember(MemberDto info); // string id

    void insertMember(MemberDto info);
}

