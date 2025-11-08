package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.WorkoutProgramService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutProgramRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workoutProgram")
@AllArgsConstructor
public class WorkoutProgramController {

    private final WorkoutProgramService workoutProgramService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody CreateWorkoutProgramRequest request) {
        return ResponseEntity.ok(workoutProgramService.create(request));
    }
}
