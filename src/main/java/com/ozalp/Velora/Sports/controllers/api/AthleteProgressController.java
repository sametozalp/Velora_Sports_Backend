package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.entities.enums.AthleteProgressStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/athleteProgress")
@AllArgsConstructor
public class AthleteProgressController {

    private final AthleteProgressService athleteProgressService;

    @PostMapping("/setStatus/{athleteId}/{athleteProgressId}/{athleteProgressStatus}")
    ResponseEntity<?> setStatus(@PathVariable UUID athleteId, @PathVariable UUID athleteProgressId, @PathVariable AthleteProgressStatus athleteProgressStatus) {
        return ResponseEntity.ok(athleteProgressService.setStatus(athleteId, athleteProgressId, athleteProgressStatus));
    }

}
