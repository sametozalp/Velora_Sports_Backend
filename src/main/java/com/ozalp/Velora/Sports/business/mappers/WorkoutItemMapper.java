package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutItemRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateWorkoutItemResponse;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutItemMapper extends BaseMapper<WorkoutItem, CreateWorkoutItemRequest, CreateWorkoutItemResponse> {
}
