package com.petplatform.dto;

import lombok.Data;

@Data
public class RefreshTokenDto {
    private String admRefreshTokenIdx;
    private String admIdx;
    private String refreshToken;
}
