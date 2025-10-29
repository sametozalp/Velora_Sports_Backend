package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateWorkoutProgramResponse extends BaseResponse {

    private UUID id;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
