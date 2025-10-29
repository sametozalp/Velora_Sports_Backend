package com.ozalp.Velora.Sports.business.dtos.requests;

import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleRequest extends BaseRequest {

    @NotNull
    private RoleEnum name;

}
