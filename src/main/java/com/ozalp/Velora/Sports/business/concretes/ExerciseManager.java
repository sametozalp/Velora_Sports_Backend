package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.ExerciseService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateExerciseRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateExerciseResponse;
import com.ozalp.Velora.Sports.business.mappers.ExerciseMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.Exercise;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.dataAcess.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public Exercise findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.ExerciseMessages.NOT_FOUND));
    }

    @Override
    public CreateExerciseResponse create(CreateExerciseRequest request) {
        Exercise exercise = mapper.toEntity(request);
        return mapper.toResponse(repository.save(exercise));
    }
}
