package com.petplatform.controller;

import com.petplatform.dto.MemberDto;
import com.petplatform.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired  //mapper를 자동으로 연결? 정확한 정의 모름
    public MemberMapper memberService;

    @GetMapping("/login")   // get타입으로 매핑
    public MemberDto selectMember(){
        //매퍼에서 selectMember 함수를 실행시켜서 받아온 MemberDto를 member1 객체로 선언
        MemberDto member1 = memberService.selectMember("haksung59");

        System.out.println("hi2");

        return member1; // MemberDto객체 member1 반환
    }

}