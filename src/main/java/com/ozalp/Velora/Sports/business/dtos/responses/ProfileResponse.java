package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse {

    private CreateUserResponse user;

    private int totalPoint;
}
