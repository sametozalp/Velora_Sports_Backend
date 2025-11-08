package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.ExerciseService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateExerciseRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercise")
@AllArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody CreateExerciseRequest request) {
        return ResponseEntity.ok(exerciseService.create(request));
    }
}
