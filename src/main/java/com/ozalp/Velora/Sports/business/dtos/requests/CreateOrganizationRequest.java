package com.ozalp.Velora.Sports.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrganizationRequest extends BaseRequest {

    @NotBlank
    @NotNull
    private String name;

}
