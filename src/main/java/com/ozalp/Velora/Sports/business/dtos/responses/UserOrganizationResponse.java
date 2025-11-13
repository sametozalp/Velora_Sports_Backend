package com.ozalp.Velora.Sports.business.dtos.responses;

import com.ozalp.Velora.Sports.entities.enums.UserOrganizationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserOrganizationResponse {

    private UUID id;

    private UserOrganizationStatus status;

}
