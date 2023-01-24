package com.petplatform.service;

import com.petplatform.dto.BoardDto;
import com.petplatform.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    public BoardMapper mapper;

    public BoardDto contentInfo(String writer) {
        try {
            BoardDto boardInfo = mapper.getContentInfo(writer);

            return boardInfo;
        }

        catch (Exception e){
            System.out.println(writer);
            System.out.println("작성자 다시 확인");
            return null;
        }
    }

    public String regContent(BoardDto boardDto){
        try {
            mapper.regContent(boardDto);
            return "게시물이 등록되었습니다.";
        }catch (Exception e){
            return "다시 시도해 주세요.";
        }
    }
}
