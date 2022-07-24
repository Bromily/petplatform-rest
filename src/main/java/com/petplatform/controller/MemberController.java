package com.petplatform.controller;

import com.petplatform.dto.MemberDto;
import com.petplatform.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    public MemberMapper memberService;

    @GetMapping("/login")
    public MemberDto selectMember(){
        MemberDto member1 = memberService.selectMember("haksung59");

        System.out.println("hi2");

        return member1;
    }

}