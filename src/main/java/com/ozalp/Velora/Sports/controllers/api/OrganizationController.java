package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.OrganizationService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateOrganizationRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody CreateOrganizationRequest request) {
        return ResponseEntity.ok(organizationService.create(request));
    }
}
