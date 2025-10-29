package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateExerciseRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateExerciseResponse;
import com.ozalp.Velora.Sports.entities.concretes.Exercise;

public interface ExerciseService extends BaseService<Exercise> {

    CreateExerciseResponse create(CreateExerciseRequest request);

}
