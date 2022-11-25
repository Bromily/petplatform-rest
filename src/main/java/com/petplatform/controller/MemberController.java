package com.petplatform.controller;

import com.petplatform.common.SHA256;
import com.petplatform.dto.MemberDto;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class MemberController {

    @Autowired  //mapper를 자동으로 연결? 정확한 정의 모름
    public MemberMapper mapper;

    @PostMapping("/login")
    public MemberDto selectMember(@RequestBody MemberDto info) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
        //매퍼에서 selectMember 함수를 실행시켜서 받아온 MemberDto를 member1 객체로 선언
        MemberDto member1 = mapper.selectMember(info);
                if(member1.getPassword().equals(sha256.encrypt(info.getPassword()))) {
                    System.out.println("Hi~" + info.getUserid());
            return member1;

        }else return null; // MemberDto객체 member1 반환
    }

//    public MemberDto selectMember(@RequestParam(value = "id")String id,
//                                  @RequestParam(value = "pw")String pw){
//        //매퍼에서 selectMember 함수를 실행시켜서 받아온 MemberDto를 member1 객체로 선언
//        MemberDto member1 = mapper.selectMember(id, pw);
//
//        System.out.println("hi2");
//
//        return member1; // MemberDto객체 member1 반환
//    }

    @PostMapping("/signup")
    public void saveUser(@RequestBody UserDto info) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
//        String changePw = info.getPw();
        info.setPassword(sha256.encrypt(info.getPassword()));
        mapper.insertMember(info);
        System.out.println("Save data");
    }

//    // 암호화 클래스
//    public class SHA256 {
//
//        public String encrypt(String text) throws NoSuchAlgorithmException {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(text.getBytes());
//
//            return bytesToHex(md.digest());
//        }
//
//        private String bytesToHex(byte[] bytes) {
//            StringBuilder builder = new StringBuilder();
//            for (byte b : bytes) {
//                builder.append(String.format("%02x", b));
//            }
//            return builder.toString();
//        }
//    }
}