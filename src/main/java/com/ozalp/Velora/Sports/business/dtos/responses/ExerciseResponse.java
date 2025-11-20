package com.ozalp.Velora.Sports.business.dtos.responses;

import com.ozalp.Velora.Sports.entities.enums.Category;
import com.ozalp.Velora.Sports.entities.enums.MuscleGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ExerciseResponse {

    private UUID id;

    private String name;

    private String description;

    private Category category;

    private List<MuscleGroup> muscleGroup;

}
