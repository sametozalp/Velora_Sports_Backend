package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RefreshTokenResponse {

    private String refreshToken;
    private String accessToken;
    private UUID userId;
}
