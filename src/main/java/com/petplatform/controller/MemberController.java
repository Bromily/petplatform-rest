package com.petplatform.controller;

import com.petplatform.dto.MemberDto;
import com.petplatform.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired private MemberService member;

    @GetMapping("/")
    public String selectMember(){
        MemberDto member1 = member.selectMember("haksung");

        System.out.println(member1.getSex());

        return member1.getName();
    }

}