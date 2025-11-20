package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.OrganizationResponse;
import com.ozalp.Velora.Sports.entities.concretes.Organization;

import java.util.List;

public interface OrganizationService extends BaseService<Organization> {

    OrganizationResponse create(CreateOrganizationRequest request);

    List<OrganizationResponse> getActiveOrganizations();
}
