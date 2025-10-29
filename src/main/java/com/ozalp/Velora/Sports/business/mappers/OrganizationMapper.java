package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateOrganizationResponse;
import com.ozalp.Velora.Sports.entities.concretes.Organization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper<Organization, CreateOrganizationRequest, CreateOrganizationResponse> {
}
