package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.requests.LoginRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateUserResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.RefreshTokenResponse;

public interface AuthService {
    CreateUserResponse register(CreateUserRequest request);

    RefreshTokenResponse refreshToken(String refreshToken);

    CreateUserResponse login(LoginRequest request);
}
