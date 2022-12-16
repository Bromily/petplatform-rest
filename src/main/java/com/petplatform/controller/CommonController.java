package com.petplatform.controller;

import com.petplatform.dto.ResponseDto;
import com.petplatform.dto.UserDto;
import com.petplatform.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class CommonController {

    @Autowired
    public CommonService service;

    @PostMapping("/api/signin")
    ResponseDto signIn(@RequestBody UserDto user,
                       HttpServletRequest request,
                       HttpServletResponse response) { return service.signIn(user, request, response); }

    @PostMapping("/api/user")
    ResponseDto signUp(@RequestBody UserDto user) { return service.signUp(user); }

    @GetMapping("/api/all/doublecheck")
    ResponseDto doubleCheck(@RequestParam("userId") String userId) { return service.doubleCheck(userId); }

    @PutMapping("/api/user")
    String modifyUser(@RequestBody UserDto user){ return service.modifyUser(user); }

    @DeleteMapping("/api/user")
    String deleteUser(@RequestBody UserDto user){ return service.deleteUser(user); }

}