package com.petplatform.mapper;

import com.petplatform.dto.PetDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetMapper {

    public void addPet(PetDto pet);

    public int petCnt(String userId);

}
