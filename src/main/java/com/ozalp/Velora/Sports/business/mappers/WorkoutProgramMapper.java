package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutProgramRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateWorkoutProgramResponse;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutProgram;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutProgramMapper extends BaseMapper<WorkoutProgram, CreateWorkoutProgramRequest, CreateWorkoutProgramResponse> {
}
