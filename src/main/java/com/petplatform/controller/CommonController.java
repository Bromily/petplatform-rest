package com.petplatform.controller;

import com.petplatform.dto.UserDto;
import com.petplatform.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@RestController
public class CommonController {

    @Autowired
    public CommonService service;

    @GetMapping("/user")
    UserDto getUserInfo(@RequestParam("id") String id){ return service.getUserInfo(id); }

    @PostMapping("/user")
    UserDto postUserInfo(@RequestBody UserDto userDto){ return service.postUserInfo(userDto); }

    @PostMapping("/sign_in")
    UserDto signIn(@RequestBody UserDto userDto, HttpSession session) throws NoSuchAlgorithmException { return service.signIn(userDto, session); }

    @GetMapping("/session")
    UserDto getSession(HttpSession session){ return service.getSession(session); }

}
