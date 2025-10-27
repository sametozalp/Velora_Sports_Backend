package com.ozalp.Velora.Sports.entities.concretes;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;
import com.ozalp.Velora.Sports.entities.enums.MembershipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "memberships")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Membership extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime joined_at;

    @Column(name = "end_date")
    private LocalDateTime left_at;

    @Column(name = "membership_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MembershipStatus membershipStatus;

}