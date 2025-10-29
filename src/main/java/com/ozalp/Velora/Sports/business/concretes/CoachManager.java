package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.CoachService;
import com.ozalp.Velora.Sports.entities.concretes.Coach;
import com.ozalp.Velora.Sports.repositories.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoachManager implements CoachService {

    private final CoachRepository repository;

    @Override
    public Coach create(Coach coach) {
        return repository.save(coach);
    }
}
