package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FullUserResponse {

    private UserResponse user;

    private AthleteResponse athlete;

    private String accessToken;

    private String refreshToken;
}
