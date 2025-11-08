package com.ozalp.Velora.Sports.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateMembershipRequest {

    @NotBlank
    @NotNull
    private String organizationId;

    @NotBlank
    @NotNull
    private String userId;

    @NotNull
    private LocalDateTime joined_at;

    @NotNull
    private LocalDateTime left_at;

}
