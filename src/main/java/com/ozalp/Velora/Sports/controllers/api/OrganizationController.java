package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.OrganizationService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateOrganizationRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody CreateOrganizationRequest request) {
        return ResponseEntity.ok(organizationService.create(request));
    }

    @PreAuthorize("hasRole('ATHLETE') or hasRole('SUPER_ADMIN')")
    @GetMapping("/getActiveOrganizations")
    ResponseEntity<?> getActiveOrganizations() {
        return ResponseEntity.ok(organizationService.getActiveOrganizations());
    }
}
