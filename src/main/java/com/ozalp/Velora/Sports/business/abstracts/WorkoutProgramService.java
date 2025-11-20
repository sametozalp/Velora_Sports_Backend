package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutProgramRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.WorkoutProgramResponse;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutProgram;

public interface WorkoutProgramService extends BaseService<WorkoutProgram> {

    WorkoutProgramResponse create(CreateWorkoutProgramRequest request);

//    List<CreateWorkoutProgramResponse> findByAthleteId(UUID athleteId);
}
