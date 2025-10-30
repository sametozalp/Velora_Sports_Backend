package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.*;
import com.ozalp.Velora.Sports.business.dtos.responses.HomeFeedResponse;
import com.ozalp.Velora.Sports.business.mappers.AthleteProgressMapper;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.business.mappers.WorkoutProgramMapper;
import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FeedManager implements FeedService {

    private final AthleteService athleteService;
    private final AthleteProgressService athleteProgressService;
    private final UserMapper userMapper;
    private final WorkoutItemService workoutItemService;

    @Override
    public HomeFeedResponse getHomeFeed(UUID athleteId) {
        Athlete athlete = athleteService.findById(athleteId);
        HomeFeedResponse response = new HomeFeedResponse();
        response.setUser(userMapper.toResponse(athlete.getUser()));
       response.setWorkoutItems(workoutItemService.findByAllInToday(athleteId, LocalDate.now()));
        response.setAthleteProgresses(athleteProgressService.findByAthleteId(athleteId));
        return response;
    }
}
