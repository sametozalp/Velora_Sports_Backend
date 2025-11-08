package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.AuthService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/refreshToken/{refreshToken}")
    ResponseEntity<?> refreshToken(@PathVariable String refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}
