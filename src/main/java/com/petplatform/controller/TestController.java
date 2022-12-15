package com.petplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@RestController
public class TestController {

//    private final TestService testService;
//
//    @GetMapping("/permit-all")
//    public Object getTest() throws Exception {
//        return testService.getTest();
//    }
//
//    @Secured("ROLE_AUTH")
//    @GetMapping("/auth")
//    public Object getTest2(@AuthenticationPrincipal SecurityUser securityUser)
//            throws Exception {
//        return testService.getTest2(securityUser);
//    }

}