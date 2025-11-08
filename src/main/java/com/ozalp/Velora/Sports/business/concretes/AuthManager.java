package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AuthService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateUserResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.RefreshTokenResponse;
import com.ozalp.Velora.Sports.common.security.JwtService;
import com.ozalp.Velora.Sports.entities.concretes.RefreshToken;
import com.ozalp.Velora.Sports.entities.concretes.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
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
        response.setAccessToken(jwtService.generateAccessToken(user));
        response.setRefreshToken(jwtService.generateRefreshToken(user).getRefreshToken());
        return response;
    }

    @Transactional
    @Override
    public RefreshTokenResponse refreshToken(String refreshToken) {
        Pair<String, RefreshToken> pair = jwtService.refreshAccessToken(refreshToken);
        String accessToken = pair.a;
        RefreshToken newRefreshToken = pair.b;
        User user = pair.b.getUser();

        RefreshTokenResponse response = new RefreshTokenResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(newRefreshToken.getRefreshToken());
        response.setUserId(user.getId());
        return response;
    }
}
