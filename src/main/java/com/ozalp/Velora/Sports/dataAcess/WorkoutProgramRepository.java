package com.ozalp.Velora.Sports.dataAcess;

import com.ozalp.Velora.Sports.entities.concretes.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram, UUID> {
    List<WorkoutProgram> findByAthleteIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID athleteId,
            LocalDateTime now, LocalDateTime now2);
}
