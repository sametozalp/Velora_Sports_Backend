package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.FullUserResponse;
import com.ozalp.Velora.Sports.entities.concretes.User;

public interface UserService extends BaseService<User> {

    User create(User user);

    FullUserResponse create(CreateUserRequest request);

    User findByEmail(String email);
}
