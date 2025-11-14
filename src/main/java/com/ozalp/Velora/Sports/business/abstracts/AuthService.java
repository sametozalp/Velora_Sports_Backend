package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.requests.LoginRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.FullUserResponse;

public interface AuthService {
    FullUserResponse register(CreateUserRequest request);

    FullUserResponse refreshToken(String refreshToken);

    FullUserResponse login(LoginRequest request);
}
