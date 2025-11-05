package com.ozalp.Velora.Sports.dataAcess;

import com.ozalp.Velora.Sports.business.dtos.responses.AthleteScoreSummary;
import com.ozalp.Velora.Sports.business.dtos.responses.DailyAthleteScore;
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

    @Query("SELECT COALESCE(a.pointsEarned, 0) FROM AthleteProgress a " +
            "WHERE a.athlete.id = :athleteId")
    int getTotalPoint(@Param("athleteId") UUID athleteId);

    @Query("""
                SELECT new com.ozalp.Velora.Sports.business.dtos.responses.AthleteScoreSummary(
                    ap.athlete,
                    SUM(ap.pointsEarned)
                )
                FROM AthleteProgress ap
                WHERE ap.organization.id = :organizationId
                  AND ap.completedAt >= :startDate
                GROUP BY ap.athlete
            """)
    List<AthleteScoreSummary> getAthleteTotalScoresForLastMonth( // tüm atletlerin son bir aydaki toplam puan listesi
                                                                 @Param("organizationId") UUID organizationId,
                                                                 @Param("startDate") LocalDateTime startDate
    );

//    @Query("""
//                SELECT new com.ozalp.Velora.Sports.business.dtos.responses.DailyAthleteScore(
//                    DATE(ap.completedAt),
//                    SUM(ap.pointsEarned)
//                )
//                FROM AthleteProgress ap
//                WHERE ap.athlete.id = :athleteId
//                  AND ap.completedAt >= :startDate
//                GROUP BY DATE(ap.completedAt)
//                ORDER BY DATE(ap.completedAt)
//            """)
//    List<DailyAthleteScore> getDailyAthleteScoreDetails(
//            @Param("athleteId") UUID athleteId,
//            @Param("startDate") LocalDate startDate
//    ); // son bir atlete ait günlük toplam puan listesi
//

    @Query("""
    SELECT new com.ozalp.Velora.Sports.business.dtos.responses.DailyAthleteScore(
        CAST(ap.completedAt AS date),
        SUM(ap.pointsEarned)
    )
    FROM AthleteProgress ap
    WHERE ap.athlete.id = :athleteId
      AND ap.completedAt >= :startDate
    GROUP BY CAST(ap.completedAt AS date)
    ORDER BY CAST(ap.completedAt AS date)
""")
    List<DailyAthleteScore> getDailyAthleteScoreDetails(
            @Param("athleteId") UUID athleteId,
            @Param("startDate") LocalDateTime startDate
    );


}