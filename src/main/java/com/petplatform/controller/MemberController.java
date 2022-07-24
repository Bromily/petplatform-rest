package com.petplatform.controller;

import com.petplatform.dto.MemberDto;
import com.petplatform.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired  //mapper를 자동으로 연결? 정확한 정의 모름
    public MemberMapper memberService;

    @GetMapping("/login")   // get타입으로 매핑
    public MemberDto selectMember(){
        //매퍼에서 selectMember 함수를 실행시켜서 받아온 MemberDto를 member1 객체로 선언
        MemberDto member1 = memberService.selectMember("ljjkyung");

        System.out.println("hi2");

        return member1; // MemberDto객체 member1 반환
    }


//    @GetMapping("/signup")
//    public MemberDto saveUser() {
//        MemberDto member2 = memberService.insertMember("ljjkyung", "1234", "이재경", "M");
//        System.out.println("정보 저장됨");
//
//        return member2;
//    }

}