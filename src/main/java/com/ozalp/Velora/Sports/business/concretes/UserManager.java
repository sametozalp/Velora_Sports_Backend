package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateUserResponse;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserMapper mapper;

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public CreateUserResponse create(CreateUserRequest request) {
        return null;
    }

}
