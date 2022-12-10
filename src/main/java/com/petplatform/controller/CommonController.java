package com.petplatform.controller;

import com.petplatform.dto.ResponseDto;
import com.petplatform.dto.UserDto;
import com.petplatform.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommonController {

    @Autowired
    public CommonService service;

    @PostMapping("/signin")
    ResponseDto signIn(@RequestBody UserDto user) { return service.signIn(user); }

    @PostMapping("/user")
    String signUp(@RequestBody UserDto user) { return service.signUp(user); }

    @GetMapping("/doublecheck")
    int doubleCheck(@RequestParam("userId") String userId) { return service.doubleCheck(userId); }

    @PutMapping("/user")
    String modifyUser(@RequestBody UserDto user){ return service.modifyUser(user); }

    @DeleteMapping("/user")
    String deleteUser(@RequestBody UserDto user){ return service.deleteUser(user); }

}