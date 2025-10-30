package com.ozalp.Velora.Sports.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateAthleteProgressRequest extends BaseRequest {

    @NotNull
    @NotBlank
    private UUID workoutProgramId;

    @NotNull
    @NotBlank
    private UUID exerciseId;

    @NotNull
    @NotBlank
    private String notes;

    @NotNull
    private int sets;

    @NotNull
    private int reps;

    @NotNull
    private int restSeconds;

    @NotNull
    private int point;

}
