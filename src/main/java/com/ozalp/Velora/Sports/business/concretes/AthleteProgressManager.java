package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.business.mappers.AthleteProgressMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.AthleteProgressRepository;
import com.ozalp.Velora.Sports.entities.concretes.AthleteProgress;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;
import com.ozalp.Velora.Sports.entities.enums.AthleteProgressStatus;
import com.ozalp.Velora.Sports.entities.enums.PointType;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AthleteProgressManager implements AthleteProgressService {

    private final AthleteProgressMapper mapper;
    private final AthleteProgressRepository repository;

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
}
