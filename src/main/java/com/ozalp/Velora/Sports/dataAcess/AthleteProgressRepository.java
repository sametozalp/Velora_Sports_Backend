package com.ozalp.Velora.Sports.dataAcess;

import com.ozalp.Velora.Sports.entities.concretes.AthleteProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AthleteProgressRepository extends JpaRepository<AthleteProgress, UUID> {
    List<AthleteProgress> findByAthleteId(UUID athleteId);

    @Query("SELECT COALESCE(SUM(r.pointsEarned), 0) FROM AthleteProgress r " +
            "WHERE r.athlete.id = :athleteId " +
            "AND r.completedAt >= :oneMonthAgo")
    int getTotalPointsLastMonth(@Param("athleteId") UUID athleteId,
                                @Param("oneMonthAgo") LocalDateTime oneMonthAgo);


    int countByAthleteIdAndCompletedAtIsNotNull(UUID athleteId); // başarısız olma sayısı

    int countByAthleteIdAndCompletedAtIsNull(UUID athleteId); // başarılı olma sayısı

    int countByAthleteIdAndCompletedAtBetween(UUID athleteId, LocalDateTime startOfDay, LocalDateTime endOfDay); // bugünkü tamamlananlar

    int countByAthleteIdAndCompletedAtIsNullAndCompletedAtBetween(UUID athleteId, LocalDateTime startOfDay, LocalDateTime endOfDay); // bugünkü tamamlanmayanlar

}