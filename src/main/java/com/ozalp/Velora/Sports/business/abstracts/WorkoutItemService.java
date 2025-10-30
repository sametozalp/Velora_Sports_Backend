package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutItemRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateWorkoutItemResponse;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface WorkoutItemService extends BaseService<WorkoutItem> {

    CreateWorkoutItemResponse create(CreateWorkoutItemRequest request);

    List<CreateWorkoutItemResponse> findByAllInToday(UUID athleteId, LocalDate date);
}
