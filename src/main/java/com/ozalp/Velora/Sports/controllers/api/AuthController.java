package com.ozalp.Velora.Sports.controllers.api;

import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/createToken")
    ResponseEntity<?> createToken() {
        User user = userService.findById(UUID.fromString("686aaadf-3655-4bf0-9643-f14da9dca501"));
        return ResponseEntity.ok(jwtService.generateToken(user));
    }
}
