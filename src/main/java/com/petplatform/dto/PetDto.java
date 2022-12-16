package com.petplatform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class PetDto {

    @Schema(description = "유저 ID")
    private String userId;

    @Schema(description = "펫 index")
    private int petIdx;

    @Schema(description = "견종")
    private String kind;

    @Schema(description = "반려동물 사진")
    private String thumbnail;

    @Schema(description = "유치원 번호")
    private String academyIdx;

    @Schema(description = "반 번호")
    private String classIdx;

    @Schema(description = "반려동물 이름")
    private String name;

    @Schema(description = "반려동물 생년월일")
    private String birth;

    @Schema(description = "반려동물 성별")
    private String sex;

    @Schema(description = "비고")
    private String petRmk;

}
