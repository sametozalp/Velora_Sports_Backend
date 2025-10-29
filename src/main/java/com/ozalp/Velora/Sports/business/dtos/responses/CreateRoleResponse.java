package com.ozalp.Velora.Sports.business.dtos.responses;

import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateRoleResponse {

    private UUID id;

    private RoleEnum name;

}
