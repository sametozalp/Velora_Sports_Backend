package com.ozalp.Velora.Sports.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateWorkoutProgramRequest {

    private UUID coachId;

    private UUID athleteId;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
