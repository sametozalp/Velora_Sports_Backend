package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateRoleRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateRoleResponse;
import com.ozalp.Velora.Sports.entities.concretes.Role;
import com.ozalp.Velora.Sports.entities.enums.RoleEnum;

public interface RoleService extends BaseService<Role> {

    CreateRoleResponse create(CreateRoleRequest request);

    CreateRoleResponse create(RoleEnum request);

}
