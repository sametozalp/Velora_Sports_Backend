package com.ozalp.Velora.Sports.dataAcess;

import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, UUID> {
}
