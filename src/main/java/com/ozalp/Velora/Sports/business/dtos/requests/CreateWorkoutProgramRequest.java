package com.ozalp.Velora.Sports.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateWorkoutProgramRequest {

    private String coachId;

    private String athleteId;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
