package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.business.abstracts.AthleteService;
import com.ozalp.Velora.Sports.business.abstracts.FeedService;
import com.ozalp.Velora.Sports.business.abstracts.WorkoutItemService;
import com.ozalp.Velora.Sports.business.dtos.responses.HomeFeedResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.ProfileResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.StaticsFeedResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.TaskFeedResponse;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "homeFeed", key = "#athleteId")
    @Override
    public HomeFeedResponse getHomeFeed(UUID athleteId) {
        Athlete athlete = athleteService.findById(athleteId);
        HomeFeedResponse response = new HomeFeedResponse();
        response.setUser(userMapper.toResponse(athlete.getUser()));
        response.setWorkoutItems(workoutItemService.findByAllInToday(athleteId, LocalDate.now()));
        response.setTotalPoint(athleteProgressService.getTotalPointsLastMonth(athleteId));
        response.setCompletedRate(athleteProgressService.getCompletedTaskRateToday(athleteId));
        return response;
    }

    @Override
    @Cacheable(value = "taskFeed", key = "#athleteId")
    public TaskFeedResponse getTaskFeed(UUID athleteId) {
        TaskFeedResponse response = new TaskFeedResponse();
        response.setWorkoutItems(workoutItemService.findByAllInToday(athleteId, LocalDate.now()));
        return response;
    }

    @Override
    @Cacheable(value = "profileFeed", key = "#athleteId")
    public ProfileResponse getProfileFeed(UUID athleteId) {
        ProfileResponse response = new ProfileResponse();
        response.setUser(userMapper.toResponse(athleteService.findById(athleteId).getUser()));
        response.setTotalPoint(athleteProgressService.getTotalPointsLastMonth(athleteId));
        response.setGeneralSuccessRate(athleteProgressService.getSuccessRate(athleteId));
        return response;
    }

    @Override
    @Cacheable(value = "staticFeed", key = "#athleteId")
    public StaticsFeedResponse getStaticsFeed(UUID athleteId, UUID organizationId) {
        return new StaticsFeedResponse(
                athleteProgressService.getDailyAthleteScoreDetails(athleteId),
                athleteProgressService.getLastMonthScores(organizationId)
        );
    }
}
