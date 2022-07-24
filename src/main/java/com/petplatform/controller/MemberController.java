package com.petplatform.controller;

import com.petplatform.dto.MemberDto;
import com.petplatform.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired  //mapper를 자동으로 연결? 정확한 정의 모름
    public MemberMapper memberService;

    //@RequestParam을 써서 id,pw를 가져와서 비번 맞으면 가입 된 MemberDto객체 반환.
    //암호화
    @GetMapping("/login")   // get타입으로 매핑
    public MemberDto selectMember(){
        //매퍼에서 selectMember 함수를 실행시켜서 받아온 MemberDto를 member1 객체로 선언
        MemberDto member1 = memberService.selectMember("ljjkyung");

        System.out.println("hi2");

        return member1; // MemberDto객체 member1 반환
    }

    //@RequestBody를 사용해서 객체를 받아와서 데이터베이스 insert(회원가입) 시키기
    //암호화
    @GetMapping("/signup")
    public void saveUser() {
        memberService.insertMember("cksdlr7446", "1234", "안찬익", "M");
        System.out.println("정보 저장됨");
    }

}