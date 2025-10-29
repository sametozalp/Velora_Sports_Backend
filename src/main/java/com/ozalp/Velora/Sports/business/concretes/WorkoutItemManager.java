package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.WorkoutItemService;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.repositories.WorkoutItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WorkoutItemManager implements WorkoutItemService {

    private final WorkoutItemRepository repository;

    @Override
    public WorkoutItem create(WorkoutItem workoutItem) {
        return repository.save(workoutItem);
    }

    @Override
    public WorkoutItem findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.WorkoutItemMessages.NOT_FOUND));
    }
}
