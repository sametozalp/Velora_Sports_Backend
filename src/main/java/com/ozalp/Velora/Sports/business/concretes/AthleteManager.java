package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.dataAcess.AthleteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AthleteManager implements AthleteService {

    private final AthleteRepository repository;
    private final UserService userService;

    @Override
    public Athlete create(Athlete athlete) {
        return repository.save(athlete);
    }

    @Override
    public Athlete findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.AthleteMessages.NOT_FOUND));
    }

    @Override
    public Athlete save(Athlete athlete) {
        return repository.save(athlete);
    }

    @Override
    public Athlete setAthlete(UUID id) {
        User user = userService.findById(id);
        Athlete athlete = new Athlete();
        athlete.setUser(user);
        return repository.save(athlete);
    }
}
