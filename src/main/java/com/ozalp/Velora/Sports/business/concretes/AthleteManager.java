package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteService;
import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import com.ozalp.Velora.Sports.repositories.AthleteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AthleteManager implements AthleteService {

    private final AthleteRepository repository;

    @Override
    public Athlete create(Athlete athlete) {
        return repository.save(athlete);
    }
}
