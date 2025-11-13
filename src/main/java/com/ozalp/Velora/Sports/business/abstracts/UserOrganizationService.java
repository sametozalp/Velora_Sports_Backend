package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.UserOrganizationResponse;
import com.ozalp.Velora.Sports.entities.concretes.UserOrganization;

public interface UserOrganizationService extends BaseService<UserOrganization> {
    UserOrganizationResponse create(CreateUserOrganizationRequest request);
}
