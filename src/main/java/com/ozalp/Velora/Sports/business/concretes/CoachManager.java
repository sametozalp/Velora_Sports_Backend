package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.CoachService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.dtos.responses.CoachResponse;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.Coach;
import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.repositories.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CoachManager implements CoachService {

    private final CoachRepository repository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public Coach create(Coach coach) {
        return repository.save(coach);
    }

    @Override
    public Coach findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.CoachMessages.NOT_FOUND));
    }

    @Override
    public CoachResponse setCoach(UUID id) {
        User user = userService.findById(id);
        Coach coach = repository.save(new Coach(user));
        CoachResponse response = new CoachResponse();
        response.setUser(userMapper.toResponse(coach.getUser()));
        return response;
    }
}
