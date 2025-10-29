package com.ozalp.Velora.Sports.business.dtos.requests;

import com.ozalp.Velora.Sports.entities.concretes.Exercise;
import jakarta.validation.constraints.NotNull;

public class CreateWorkoutItemRequest extends BaseRequest {

    @NotNull
    private String workoutProgramId;

    @NotNull
    private Exercise exercise;

    private String notes;

    @NotNull
    private int sets;

    @NotNull
    private int reps;

    @NotNull
    private int restSeconds;

}
