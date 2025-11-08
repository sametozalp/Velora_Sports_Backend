package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MinimalUserResponse {

    private UUID id;

    private String firstName;

    private String lastName;
}
