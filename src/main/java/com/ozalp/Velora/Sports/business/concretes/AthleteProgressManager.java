package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.business.abstracts.AthleteService;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateAthleteProgressResponse;
import com.ozalp.Velora.Sports.business.mappers.AthleteProgressMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.AthleteProgressRepository;
import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import com.ozalp.Velora.Sports.entities.concretes.AthleteProgress;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import com.ozalp.Velora.Sports.entities.enums.AthleteProgressStatus;
import com.ozalp.Velora.Sports.entities.enums.PointType;
import com.ozalp.Velora.Sports.exceptions.errors.AuthorizationException;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                workoutItem.getPoint()
        );

        return repository.save(athleteProgress);
    }

    @Override
    public CreateAthleteProgressResponse setStatus(UUID athleteId, UUID athleteProgressId, AthleteProgressStatus athleteProgressStatus) {
        AthleteProgress athleteProgress = findById(athleteProgressId);
        Athlete athlete = athleteProgress.getAthlete();
        if (!athlete.getId().equals(athleteService.findById(athleteId).getId())) {
            throw new AuthorizationException(Messages.AthleteProgress.NOT_MATCHED);
        }

        athleteProgress.setAthleteProgressStatus(athleteProgressStatus);
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

//    @Override
//    public int countByAthleteIdAndCreatedAtAfter(UUID athleteId) {
//        return repository.getTotalPointsLastMonth(athleteId, LocalDateTime.now().minusMonths(1));
//    }
}
