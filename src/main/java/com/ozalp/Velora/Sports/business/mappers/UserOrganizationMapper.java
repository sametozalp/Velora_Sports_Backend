package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.UserOrganizationResponse;
import com.ozalp.Velora.Sports.entities.concretes.UserOrganization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserOrganizationMapper extends BaseMapper<UserOrganization, CreateUserOrganizationRequest, UserOrganizationResponse> {
}
