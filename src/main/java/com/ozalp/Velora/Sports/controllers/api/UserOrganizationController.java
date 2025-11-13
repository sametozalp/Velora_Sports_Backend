package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.UserOrganizationService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserOrganizationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userOrganization")
@AllArgsConstructor
public class UserOrganizationController {

    private final UserOrganizationService userOrganizationService;

    @PostMapping("/create")
    ResponseEntity<?> create(CreateUserOrganizationRequest request) {
        return ResponseEntity.ok(userOrganizationService.create(request));
    }
}
