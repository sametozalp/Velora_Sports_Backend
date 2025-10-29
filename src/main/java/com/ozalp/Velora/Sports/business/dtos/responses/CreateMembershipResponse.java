package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateMembershipResponse {

    private UUID id;

    private LocalDateTime joined_at;

    private LocalDateTime left_at;

}
