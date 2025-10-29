package com.ozalp.Velora.Sports.business.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateWorkoutItemRequest extends BaseRequest {

    @NotNull
    private UUID workoutProgramId;

    @NotNull
    private UUID exerciseId;

    private String notes;

    @NotNull
    private int sets;

    @NotNull
    private int reps;

    @NotNull
    private int restSeconds;

}
