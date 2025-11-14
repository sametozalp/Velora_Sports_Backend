package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.UserResponse;
import com.ozalp.Velora.Sports.entities.concretes.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, CreateUserRequest, UserResponse> {

}
