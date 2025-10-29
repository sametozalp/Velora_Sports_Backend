package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.responses.CoachResponse;
import com.ozalp.Velora.Sports.entities.concretes.Coach;

import java.util.UUID;

public interface CoachService extends BaseService<Coach> {

    CoachResponse setCoach(UUID id);
}
