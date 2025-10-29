package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutItemRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateWorkoutItemResponse;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;

public interface WorkoutItemService extends BaseService<WorkoutItem> {

    CreateWorkoutItemResponse create(CreateWorkoutItemRequest request);

}
