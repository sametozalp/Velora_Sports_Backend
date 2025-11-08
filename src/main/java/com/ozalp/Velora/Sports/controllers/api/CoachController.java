package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/coach")
@AllArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @PreAuthorize("hasRole('ROLE_ORG_ADMIN')")
    @PostMapping("/create/{userId}")
    ResponseEntity<?> create(@PathVariable UUID userId) {
        return ResponseEntity.ok(coachService.setCoach(userId));
    }
}
