package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AuthService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.requests.LoginRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.FullUserResponse;
import com.ozalp.Velora.Sports.business.mappers.AthleteMapper;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.common.security.JwtService;
import com.ozalp.Velora.Sports.entities.concretes.RefreshToken;
import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.exceptions.errors.NotMatchedException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AthleteMapper athleteMapper;

    @Transactional
    @Override
    public FullUserResponse register(CreateUserRequest request) {
        FullUserResponse response = userService.create(request);
        User user = userService.findById(response.getUser().getId());
        response.setAccessToken(jwtService.generateAccessToken(user));
        response.setRefreshToken(jwtService.generateRefreshToken(user).getRefreshToken());
        return response;
    }

    @Transactional
    @Override
    public FullUserResponse refreshToken(String refreshToken) {
        Pair<String, RefreshToken> pair = jwtService.refreshAccessToken(refreshToken);
        String accessToken = pair.a;
        RefreshToken newRefreshToken = pair.b;
        User user = pair.b.getUser();

        return FullUserResponse.builder()
                .refreshToken(newRefreshToken.getRefreshToken())
                .accessToken(accessToken)
                .user(userMapper.toResponse(user))
                .athlete(athleteMapper.toResponse(user.getAthlete()))
                .build();

//        RefreshTokenResponse response = new RefreshTokenResponse();
//        response.setAccessToken(accessToken);
//        response.setRefreshToken(newRefreshToken.getRefreshToken());
//        response.setUserId(user.getId());
//        return response;
    }

    @Override
    public FullUserResponse login(LoginRequest request) {
        User user = userService.findByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new NotMatchedException(Messages.AuthMessages.PASSWORDS_NOT_MATCHED);
        }

        return FullUserResponse.builder()
                .user(userMapper.toResponse(user))
                .athlete(athleteMapper.toResponse(user.getAthlete()))
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user).getRefreshToken())
                .build();
    }
}
