package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.aop.annotations.CheckCoachOwnerShip;
import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.business.abstracts.ExerciseService;
import com.ozalp.Velora.Sports.business.abstracts.WorkoutItemService;
import com.ozalp.Velora.Sports.business.abstracts.WorkoutProgramService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutItemRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.WorkoutItemResponse;
import com.ozalp.Velora.Sports.business.mappers.WorkoutItemMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.WorkoutItemRepository;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    @Transactional
    @Override
    @CheckCoachOwnerShip
    public WorkoutItemResponse create(CreateWorkoutItemRequest request) {
        WorkoutItem workoutItem = mapper.toEntity(request);
        workoutItem.setExercise(exerciseService.findById(request.getExerciseId()));
        workoutItem.setWorkoutProgram(workoutProgramService.findById(request.getWorkoutProgramId()));
        workoutItem.setAthlete(workoutProgramService.findById(request.getWorkoutProgramId()).getAthlete());
        WorkoutItem saved = repository.save(workoutItem);
        athleteProgressService.save(saved);
        return mapper.toResponse(saved);
    }

    @Override
    public List<WorkoutItemResponse> findByAllInToday(UUID athleteId, LocalDate date) {
        return repository.findByAthleteIdAndDate(athleteId, date)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
