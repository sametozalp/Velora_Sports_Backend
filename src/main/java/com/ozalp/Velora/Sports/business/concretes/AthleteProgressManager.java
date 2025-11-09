package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.aop.annotations.CheckAthleteOwnership;
import com.ozalp.Velora.Sports.aop.annotations.Loggable;
import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.business.abstracts.AthleteService;
import com.ozalp.Velora.Sports.business.dtos.responses.AthleteScoreSummary;
import com.ozalp.Velora.Sports.business.dtos.responses.AthleteScoreSummaryResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateAthleteProgressResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.DailyAthleteScore;
import com.ozalp.Velora.Sports.business.mappers.AthleteProgressMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.AthleteProgressRepository;
import com.ozalp.Velora.Sports.entities.concretes.AthleteProgress;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import com.ozalp.Velora.Sports.entities.enums.AthleteProgressStatus;
import com.ozalp.Velora.Sports.entities.enums.PointType;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AthleteProgressManager implements AthleteProgressService {

    private final AthleteProgressMapper mapper;
    private final AthleteProgressRepository repository;
    private final AthleteService athleteService;

    @Override
    public AthleteProgress create(AthleteProgress athleteProgress) {
        return repository.save(athleteProgress);
    }

    @Override
    public AthleteProgress findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.AthleteProgress.NOT_FOUND));
    }

    @Override
    public AthleteProgress save(AthleteProgress athleteProgress) {
        return repository.save(athleteProgress);
    }

    @Override
    public AthleteProgress save(WorkoutItem workoutItem) {
        AthleteProgress athleteProgress = new AthleteProgress(
                workoutItem.getWorkoutProgram().getAthlete(),
                PointType.EXERCISE,
                workoutItem.getId(),
                AthleteProgressStatus.NOT_COMPLETED,
                workoutItem.getPoint(),
                workoutItem.getAthlete().getUser().getOrganization()
        );

        return repository.save(athleteProgress);
    }

    @Override
    @CheckAthleteOwnership
    public CreateAthleteProgressResponse setStatus(UUID athleteId, UUID athleteProgressId, AthleteProgressStatus athleteProgressStatus) {
        AthleteProgress athleteProgress = findById(athleteProgressId);
        athleteProgress.setAthleteProgressStatus(athleteProgressStatus);
        if (athleteProgressStatus == AthleteProgressStatus.COMPLETED) {
            athleteProgress.setCompletedAt(LocalDateTime.now());
        } else {
            athleteProgress.setCompletedAt(null);
        }
        return mapper.toResponse(save(athleteProgress));
    }

    @Override
    public List<CreateAthleteProgressResponse> findByAthleteId(UUID athleteId) {
        return repository.findByAthleteId(athleteId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public int getTotalPointsLastMonth(UUID athleteId) {
        return repository.getTotalPointsLastMonth(athleteId, LocalDateTime.now().minusMonths(1));
    }

    @Override
    public int getSuccessRate(UUID athleteId) {
        int successCount = repository.countByAthleteIdAndCompletedAtIsNotNull(athleteId);
        int failedCount = repository.countByAthleteIdAndCompletedAtIsNull(athleteId);
        int totalCount = successCount + failedCount;
        if (totalCount == 0) {
            return 0;
        }
        return (successCount / totalCount) * 100;
    }

    @Override
    public int getCompletedTaskRateToday(UUID athleteId) {
        int countCompletedTask = repository.countByAthleteIdAndCompletedAtBetween(athleteId, LocalDateTime.now(), LocalDateTime.now());
        int countNotCompletedTask = repository.countByAthleteIdAndCompletedAtIsNullAndCompletedAtBetween(athleteId, LocalDateTime.now(), LocalDateTime.now());
        int totalCount = countCompletedTask + countNotCompletedTask;
        if (totalCount == 0) {
            return 0;
        }
        return (countCompletedTask / totalCount) * 100;
    }

    @Override
    public List<AthleteScoreSummaryResponse> getLastMonthScores(UUID organizationId) {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(1);
        List<AthleteScoreSummary> athleteTotalScoresForLastMonth =
                repository.getAthleteTotalScoresForLastMonth(organizationId, startDate);
        List<AthleteScoreSummaryResponse> responses = new ArrayList<>();
        for (AthleteScoreSummary summary : athleteTotalScoresForLastMonth) {
            responses.add(new AthleteScoreSummaryResponse(
                    summary.getAthlete().getId(),
                    summary.getAthlete().getUser().getFirstName(),
                    summary.getAthlete().getUser().getLastName(),
                    summary.getTotalScore()
            ));
        }
        return responses;
    }

    @Override
    public List<DailyAthleteScore> getDailyAthleteScoreDetails(UUID athleteId) {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        List<DailyAthleteScore> sqlResults = repository.getDailyAthleteScoreDetails(athleteId, oneMonthAgo);
        return sqlResults.stream()
                .map(r -> new DailyAthleteScore(
                        r.getDate(),
                        r.getTotalPoints()
                ))
                .toList();
    }


//    @Override
//    public int countByAthleteIdAndCreatedAtAfter(UUID athleteId) {
//        return repository.getTotalPointsLastMonth(athleteId, LocalDateTime.now().minusMonths(1));
//    }
}
