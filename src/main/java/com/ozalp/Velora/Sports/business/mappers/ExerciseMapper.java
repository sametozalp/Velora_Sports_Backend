package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateExerciseRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.ExerciseResponse;
import com.ozalp.Velora.Sports.entities.concretes.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper extends BaseMapper<Exercise, CreateExerciseRequest, ExerciseResponse> {
}
