package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateRoleRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.RoleResponse;
import com.ozalp.Velora.Sports.entities.concretes.Role;
import com.ozalp.Velora.Sports.entities.enums.RoleEnum;

public interface RoleService extends BaseService<Role> {

    RoleResponse create(CreateRoleRequest request);

    RoleResponse create(RoleEnum request);

    Role findByName(RoleEnum name);

}
