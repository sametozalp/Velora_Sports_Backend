package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.business.abstracts.AthleteService;
import com.ozalp.Velora.Sports.business.abstracts.FeedService;
import com.ozalp.Velora.Sports.business.abstracts.WorkoutItemService;
import com.ozalp.Velora.Sports.business.dtos.responses.HomeFeedResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.ProfileResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.TaskFeedResponse;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
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
        response.setTotalPoint(athleteProgressService.getTotalPointsLastMonth(athleteId));
        return response;
    }

    @Override
    public TaskFeedResponse getTaskFeed(UUID athleteId) {
        TaskFeedResponse response = new TaskFeedResponse();
        response.setWorkoutItems(workoutItemService.findByAllInToday(athleteId, LocalDate.now()));
        return response;
    }

    @Override
    public ProfileResponse getProfileFeed(UUID athleteId) {
        ProfileResponse response = new ProfileResponse();
        response.setUser(userMapper.toResponse(athleteService.findById(athleteId).getUser()));
        response.setTotalPoint(athleteProgressService.getTotalPointsLastMonth(athleteId));
        return response;
    }
}
