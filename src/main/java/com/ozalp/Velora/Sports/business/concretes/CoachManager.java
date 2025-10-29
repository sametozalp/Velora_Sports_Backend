package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.CoachService;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.Coach;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.repositories.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CoachManager implements CoachService {

    private final CoachRepository repository;

    @Override
    public Coach create(Coach coach) {
        return repository.save(coach);
    }

    @Override
    public Coach findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.CoachMessages.NOT_FOUND));
    }
}
