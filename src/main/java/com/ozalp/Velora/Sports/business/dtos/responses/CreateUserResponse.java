package com.ozalp.Velora.Sports.business.dtos.responses;

import com.ozalp.Velora.Sports.entities.concretes.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class CreateUserResponse extends BaseResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<UserRoleResponse> roles;
}
