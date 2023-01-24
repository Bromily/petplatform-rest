package com.petplatform.mapper;

import com.petplatform.dto.BoardDto;
import com.petplatform.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    BoardDto getContentInfo(String writer);

    void regContent(BoardDto boardDto);
}
