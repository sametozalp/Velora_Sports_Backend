package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.entities.concretes.Athlete;

import java.util.UUID;

public interface AthleteService extends BaseService<Athlete> {

    Athlete setAthlete(UUID id);
}
