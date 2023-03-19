package com.petplatform.controller;

import com.petplatform.dto.UserDto;
import com.petplatform.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommonController {

    @Autowired
    public CommonService service;

    @PostMapping("/sign_in")
    UserDto signIn(@RequestBody UserDto user) { return service.signIn(user); }

    @PostMapping("/user")
    String signUp(@RequestBody UserDto user) { return service.signUp(user); }

    @GetMapping("/double_check")
    int doubleCheck(@RequestParam("userId") String userId) { return service.doubleCheck(userId); }

    @PutMapping("/user_mo")
    String modifyUser(@RequestBody UserDto user){ return service.modifyUser(user); }

    @DeleteMapping("/user_de")
    String deleteUser(@RequestBody UserDto user){ return service.deleteUser(user); }

}
