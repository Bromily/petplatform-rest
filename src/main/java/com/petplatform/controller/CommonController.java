package com.petplatform.controller;

import com.petplatform.dto.UserDto;
import com.petplatform.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class CommonController {

    @Autowired
    public CommonService service;

    @PostMapping("/sign_in")
    UserDto signIn(@RequestBody UserDto user) throws NoSuchAlgorithmException { return service.signIn(user); }

}