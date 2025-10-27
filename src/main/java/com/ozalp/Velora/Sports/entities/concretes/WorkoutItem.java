package com.ozalp.Velora.Sports.entities.concretes;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workout_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkoutItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "workout_program_id", nullable = false)
    private WorkoutProgram workoutProgram;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "notes")
    private String notes;

    @Column(name = "sets", nullable = false)
    private int sets;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "rest_seconds", nullable = false)
    private int restSeconds;

}
