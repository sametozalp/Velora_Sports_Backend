package com.ozalp.Velora.Sports.business.dtos.requests;

import com.ozalp.Velora.Sports.entities.enums.Category;
import com.ozalp.Velora.Sports.entities.enums.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateExerciseRequest extends BaseRequest {

    @NotBlank
    @NotNull
    private String name;

    private String description;

    @NotNull
    private Category category;

    @NotNull
    private List<MuscleGroup> muscleGroup;

}
