package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.entities.concretes.AthleteProgress;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutItem;

public interface AthleteProgressService extends BaseService<AthleteProgress> {

    AthleteProgress save(WorkoutItem workoutItem);
}
