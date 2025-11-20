package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateRoleRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.RoleResponse;
import com.ozalp.Velora.Sports.entities.concretes.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, CreateRoleRequest, RoleResponse> {
}
