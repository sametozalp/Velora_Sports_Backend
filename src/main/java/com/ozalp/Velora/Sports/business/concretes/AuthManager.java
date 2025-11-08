package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AuthService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateUserResponse;
import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.common.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Transactional
    @Override
    public CreateUserResponse register(CreateUserRequest request) {
        CreateUserResponse response = userService.create(request);
        User user = userService.findById(response.getId());
        response.setAccessToken(jwtService.generateToken(user));
        return response;
    }
}
