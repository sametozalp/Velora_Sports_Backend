package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse {

    private UserResponse user;

    private int totalPoint;

    private int generalSuccessRate;

}
