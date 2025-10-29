package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.ExerciseService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateExerciseRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateExerciseResponse;
import com.ozalp.Velora.Sports.business.mappers.ExerciseMapper;
import com.ozalp.Velora.Sports.entities.concretes.Exercise;
import com.ozalp.Velora.Sports.repositories.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExerciseManager implements ExerciseService {

    private final ExerciseRepository repository;
    private final ExerciseMapper mapper;

    @Override
    public Exercise create(Exercise exercise) {
        return repository.save(exercise);
    }

    @Override
    public CreateExerciseResponse create(CreateExerciseRequest request) {
        Exercise exercise = mapper.toEntity(request);
        return mapper.toResponse(repository.save(exercise));
    }
}
