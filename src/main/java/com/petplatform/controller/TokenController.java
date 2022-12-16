package com.petplatform.controller;

import com.petplatform.dto.UserDto;
import com.petplatform.security.TokenCreater;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class TokenController {

    @Autowired
    TokenCreater tokenCreater;

    // JWT 토큰 재발급
    @PostMapping("/tokenRefresh")
    public void tokenRefresh(@RequestBody UserDto user,
                                    HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse) throws Exception{
        tokenCreater.tokenRefresh(user, servletRequest, servletResponse);
    }

}