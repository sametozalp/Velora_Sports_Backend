package com.ozalp.Velora.Sports.business.dtos.responses;

import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleResponse {

    private UUID id;

    private RoleEnum name;

}
