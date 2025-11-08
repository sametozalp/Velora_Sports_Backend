package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateUserResponse;
import jakarta.validation.Valid;

public interface AuthService {
    CreateUserResponse register(@Valid CreateUserRequest request);
}
