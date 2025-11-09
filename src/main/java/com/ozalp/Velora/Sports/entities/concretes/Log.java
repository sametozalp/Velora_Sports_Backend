package com.ozalp.Velora.Sports.entities.concretes;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;
import com.ozalp.Velora.Sports.entities.enums.ExceptionLogStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log extends BaseEntity {

    @Column(name = "details")
    private String details;

    @Column(name = "exception_service")
    private String exceptionService;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ExceptionLogStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "action")
    private String action;

    @Column(name = "old_data")
    private String oldData;

    @Column(name = "new_data")
    private String newData;

    @Column(name = "execution_time_ms")
    private Long executionTimeMs;
}
