package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.business.abstracts.ExerciseService;
import com.ozalp.Velora.Sports.business.abstracts.WorkoutItemService;
import com.ozalp.Velora.Sports.business.abstracts.WorkoutProgramService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutItemRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateWorkoutItemResponse;
import com.ozalp.Velora.Sports.business.mappers.WorkoutItemMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.dataAcess.WorkoutItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WorkoutItemManager implements WorkoutItemService {

    private final WorkoutItemRepository repository;
    private final WorkoutItemMapper mapper;
    private final ExerciseService exerciseService;
    private final WorkoutProgramService workoutProgramService;
    private final AthleteProgressService athleteProgressService;

    @Override
    public WorkoutItem create(WorkoutItem workoutItem) {
        return repository.save(workoutItem);
    }

    @Override
    public WorkoutItem findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.WorkoutItemMessages.NOT_FOUND));
    }

    @Override
    public WorkoutItem save(WorkoutItem workoutItem) {
        return repository.save(workoutItem);
    }

    @Override
    public CreateWorkoutItemResponse create(CreateWorkoutItemRequest request) {
        WorkoutItem workoutItem = mapper.toEntity(request);
        workoutItem.setExercise(exerciseService.findById(request.getExerciseId()));
        workoutItem.setWorkoutProgram(workoutProgramService.findById(request.getWorkoutProgramId()));
        return mapper.toResponse(repository.save(workoutItem));
    }
}
