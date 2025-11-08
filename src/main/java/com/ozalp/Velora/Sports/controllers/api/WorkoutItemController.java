package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.WorkoutItemService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutItemRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workoutItem")
@AllArgsConstructor
public class WorkoutItemController {

    private final WorkoutItemService workoutItemService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody CreateWorkoutItemRequest request) {
        return ResponseEntity.ok(workoutItemService.create(request));
    }
}
