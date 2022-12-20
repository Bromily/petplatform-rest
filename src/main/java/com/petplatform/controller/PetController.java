package com.petplatform.controller;

import com.petplatform.dto.PetDto;
import com.petplatform.dto.ResponseDto;
import com.petplatform.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {

    @Autowired
    private PetService service;

    @PostMapping("/api/user/add-pet")
    ResponseDto addPet(@RequestBody PetDto petDto){ return service.addPet(petDto); }

}
