package com.ozalp.Velora.Sports.entities.concretes;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;
import com.ozalp.Velora.Sports.entities.enums.PointType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "points_earned", nullable = false)
    private Integer pointsEarned = 0;
}
