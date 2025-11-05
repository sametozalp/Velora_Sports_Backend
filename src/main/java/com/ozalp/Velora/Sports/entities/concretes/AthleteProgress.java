package com.ozalp.Velora.Sports.entities.concretes;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;
import com.ozalp.Velora.Sports.entities.enums.AthleteProgressStatus;
import com.ozalp.Velora.Sports.entities.enums.PointType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "athlete_progresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AthleteProgress extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @Column(name = "point_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PointType pointType;

    @Column(name = "target_item_id", nullable = false)
    private UUID targetItemId;

    @Column(name = "athlete_progress_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AthleteProgressStatus athleteProgressStatus;

    @Column(name = "completed_at", nullable = true)
    private LocalDateTime completedAt;

    @Column(name = "points_earned", nullable = false)
    private Integer pointsEarned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public AthleteProgress(Athlete athlete, PointType pointType, UUID targetItemId, AthleteProgressStatus athleteProgressStatus, Integer pointsEarned, Organization organization) {
        this.athlete = athlete;
        this.pointType = pointType;
        this.targetItemId = targetItemId;
        this.athleteProgressStatus = athleteProgressStatus;
        this.pointsEarned = pointsEarned;
        this.organization = organization;
    }
}
