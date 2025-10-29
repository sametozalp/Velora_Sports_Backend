package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateOrganizationResponse;
import com.ozalp.Velora.Sports.entities.concretes.Organization;

public interface OrganizationService extends BaseService<Organization> {

    CreateOrganizationResponse create(CreateOrganizationRequest request);

}
