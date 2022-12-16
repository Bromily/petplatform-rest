package com.petplatform.dto;

import lombok.Data;

@Data
public class RefreshTokenDto {
    private String tokenIndex;
    private String userId;
    private String refreshToken;
}
