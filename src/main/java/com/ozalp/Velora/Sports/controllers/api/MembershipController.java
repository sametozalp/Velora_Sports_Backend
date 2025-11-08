package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.MembershipService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateMembershipRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/membership")
@AllArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @PreAuthorize("hasRole('ROLE_ORG_ADMIN')")
    @PostMapping("/create")
    ResponseEntity<?> create(@Valid @RequestBody CreateMembershipRequest request) {
        return ResponseEntity.ok(membershipService.create(request));
    }
}
