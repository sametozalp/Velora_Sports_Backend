package com.ozalp.Velora.Sports.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserOrganizationRequest {

    private UUID userId;

    private UUID organizationId;

}
