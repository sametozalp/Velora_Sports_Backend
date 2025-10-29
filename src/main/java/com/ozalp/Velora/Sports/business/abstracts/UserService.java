package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.entities.concretes.User;

public interface UserService extends BaseService<User> {

    User create(CreateUserRequest request);

}
