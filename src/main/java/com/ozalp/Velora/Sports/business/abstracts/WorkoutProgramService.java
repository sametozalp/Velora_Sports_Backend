package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutProgramRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateWorkoutProgramResponse;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutProgram;

public interface WorkoutProgramService extends BaseService<WorkoutProgram> {

    CreateWorkoutProgramResponse create(CreateWorkoutProgramRequest request);
}
