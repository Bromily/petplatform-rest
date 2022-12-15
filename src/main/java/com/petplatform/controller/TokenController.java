package com.petplatform.controller;

import com.petplatform.common.SHA256;
import com.petplatform.dto.RefreshTokenDto;
import com.petplatform.dto.ResponseDto;
import com.petplatform.dto.ResponsePost;
import com.petplatform.dto.UserDto;
import com.petplatform.mapper.TokenMapper;
import com.petplatform.security.JwtProvider;
import com.petplatform.service.TokenService;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TokenController {

    @Autowired
    TokenService service;

    @PostMapping("/api/signin")
    public ResponseDto tokenCreate(@RequestBody UserDto user,
                                   HttpServletRequest servletRequest,
                                   HttpServletResponse servletResponse) throws Exception{
        return service.tokenCreate(user, servletRequest, servletResponse);
    }

    // JWT 토큰 재발급
    @PostMapping("/tokenRefresh")
    public ResponseDto tokenRefresh(@RequestBody UserDto user,
                                    HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse) throws Exception{
        return service.tokenRefresh(user, servletRequest, servletResponse);
    }

//    @GetMapping("/rjawmd")
//    public Boolean rjawmd() {
//
//    }

//    @Data
//    class JwtRequest {
//        private String email;
//        private String password;
//    }
//
//    @Data
//    @AllArgsConstructor
//    class JwtResponse {
//        private String token;
//    }
}