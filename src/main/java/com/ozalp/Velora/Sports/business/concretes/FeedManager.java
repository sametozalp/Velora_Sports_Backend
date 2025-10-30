package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.business.abstracts.AthleteService;
import com.ozalp.Velora.Sports.business.abstracts.FeedService;
import com.ozalp.Velora.Sports.business.abstracts.WorkoutProgramService;
import com.ozalp.Velora.Sports.business.dtos.responses.HomeFeedResponse;
import com.ozalp.Velora.Sports.business.mappers.AthleteProgressMapper;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.business.mappers.WorkoutProgramMapper;
import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FeedManager implements FeedService {

    private final AthleteService athleteService;
    private final AthleteProgressService athleteProgressService;
    private final UserMapper userMapper;
    private final AthleteProgressMapper athleteProgressMapper;
    private final WorkoutProgramService workoutProgramService;

    @Override
    public HomeFeedResponse getHomeFeed(UUID athleteId) {
        Athlete athlete = athleteService.findById(athleteId);
        HomeFeedResponse response = new HomeFeedResponse();
        response.setUser(userMapper.toResponse(athlete.getUser()));
        response.setWorkoutPrograms(workoutProgramService.findByAthleteId(athleteId));
        response.setAthleteProgresses(athleteProgressService.findByAthleteId(athleteId));
        return response;
    }
}
