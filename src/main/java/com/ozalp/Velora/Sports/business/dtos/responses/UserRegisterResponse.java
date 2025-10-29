package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRegisterResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;
}
