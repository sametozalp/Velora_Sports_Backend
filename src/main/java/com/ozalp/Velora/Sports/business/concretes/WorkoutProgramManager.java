package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.WorkoutProgramService;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutProgram;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.repositories.WorkoutProgramRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WorkoutProgramManager implements WorkoutProgramService {

    private final WorkoutProgramRepository repository;

    @Override
    public WorkoutProgram create(WorkoutProgram workoutProgram) {
        return repository.save(workoutProgram);
    }

    @Override
    public WorkoutProgram findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.WorkoutProgramMessages.NOT_FOUND));
    }
}
