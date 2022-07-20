package com.petplatform.service;

import com.petplatform.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Mapper
@Repository
public interface MemberService {

    MemberDto selectMember(String id);

}
