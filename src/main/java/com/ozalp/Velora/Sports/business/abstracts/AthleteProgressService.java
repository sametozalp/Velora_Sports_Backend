package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.responses.CreateAthleteProgressResponse;
import com.ozalp.Velora.Sports.entities.concretes.AthleteProgress;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import com.ozalp.Velora.Sports.entities.enums.AthleteProgressStatus;

import java.util.List;
import java.util.UUID;

public interface AthleteProgressService extends BaseService<AthleteProgress> {

    AthleteProgress save(WorkoutItem workoutItem);

    CreateAthleteProgressResponse setStatus(UUID athleteId, UUID athleteProgressId, AthleteProgressStatus athleteProgressStatus);

    List<CreateAthleteProgressResponse> findByAthleteId(UUID athleteId);

    //int countByAthleteIdAndCreatedAtAfter(UUID athleteId); // kaç tane aktivite

    int getTotalPointsLastMonth(UUID athleteId);

    int getSuccessRate(UUID athleteId);

    int getCompletedTaskRateToday(UUID athleteId);
}
