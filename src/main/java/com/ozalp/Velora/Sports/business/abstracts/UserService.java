package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateUserResponse;
import com.ozalp.Velora.Sports.entities.concretes.User;

public interface UserService extends BaseService<User> {

    User create(User user);

    CreateUserResponse create(CreateUserRequest request);

}
