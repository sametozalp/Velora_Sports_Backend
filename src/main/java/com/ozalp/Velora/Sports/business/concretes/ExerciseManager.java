package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.ExerciseService;
import com.ozalp.Velora.Sports.entities.concretes.Exercise;
import com.ozalp.Velora.Sports.repositories.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExerciseManager implements ExerciseService {

    private final ExerciseRepository repository;

    @Override
    public Exercise create(Exercise exercise) {
        return repository.save(exercise);
    }
}
