package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateWorkoutItemResponse extends BaseResponse {

    private UUID id;

    private String notes;

    private int sets;

    private int reps;

    private int restSeconds;

}
