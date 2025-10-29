package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.WorkoutProgramService;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutProgram;
import com.ozalp.Velora.Sports.repositories.WorkoutProgramRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkoutProgramManager implements WorkoutProgramService {

    private final WorkoutProgramRepository repository;

    @Override
    public WorkoutProgram create(WorkoutProgram workoutProgram) {
        return repository.save(workoutProgram);
    }
}
