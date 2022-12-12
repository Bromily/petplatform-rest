package com.petplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReactController {

    @GetMapping("/{pathUrl}")
    public String index(@PathVariable String pathUrl){
        return pathUrl;
    }

}
