package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.WorkoutItemService;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import com.ozalp.Velora.Sports.repositories.WorkoutItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkoutItemManager implements WorkoutItemService {

    private final WorkoutItemRepository repository;

    @Override
    public WorkoutItem create(WorkoutItem workoutItem) {
        return repository.save(workoutItem);
    }
}
