package com.petplatform.controller;

import com.petplatform.dto.BoardDto;
import com.petplatform.dto.UserDto;
import com.petplatform.service.BoardService;
import com.petplatform.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

    @Autowired
    public BoardService boardService;

    @GetMapping("/get_content")
    BoardDto showContent(@RequestParam String writer) { return boardService.contentInfo(writer); }

    @PostMapping("/reg_content")
    String regContent(@RequestBody BoardDto board) { return boardService.regContent(board); }


}
