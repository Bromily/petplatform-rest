package com.petplatform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {
    private int num;
    private String title;
    private String content;
    private String writer;
}
