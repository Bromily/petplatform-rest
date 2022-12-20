package com.petplatform.service;

import com.petplatform.dto.PetDto;
import com.petplatform.dto.ResponseDto;
import com.petplatform.dto.ResponsePost;
import com.petplatform.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    PetMapper mapper;

    public ResponseDto addPet(PetDto pet){
        ResponseDto response = new ResponseDto();
        ResponsePost result = new ResponsePost();

        try {
            pet.setPetIdx(mapper.petCnt(pet.getUserId())+1);
            mapper.addPet(pet);
            result = new ResponsePost(null);
            response = new ResponseDto(result, null);
        }catch (Exception e){
            result = new ResponsePost(e);
            response = new ResponseDto(result, e);
        }

        return response;
    }

}
