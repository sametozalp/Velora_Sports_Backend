package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.UserOrganizationService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserOrganizationRequest;
import com.ozalp.Velora.Sports.entities.enums.UserOrganizationStatus;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/userOrganization")
@AllArgsConstructor
public class UserOrganizationController {

    private final UserOrganizationService userOrganizationService;

    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody CreateUserOrganizationRequest request) {
        return ResponseEntity.ok(userOrganizationService.create(request));
    }

    @PostMapping("/setStatus/{userOrganizationId}/{status}")
    ResponseEntity<?> setStatus(@PathVariable("userOrganizationId") UUID userOrganizationId, @PathVariable("status") UserOrganizationStatus status) {
        return ResponseEntity.ok(userOrganizationService.setStatus(userOrganizationId, status));
    }
}
