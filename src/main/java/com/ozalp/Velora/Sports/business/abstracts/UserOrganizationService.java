package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.UserOrganizationResponse;
import com.ozalp.Velora.Sports.entities.concretes.UserOrganization;
import com.ozalp.Velora.Sports.entities.enums.UserOrganizationStatus;

import java.util.UUID;

public interface UserOrganizationService extends BaseService<UserOrganization> {
    UserOrganizationResponse create(CreateUserOrganizationRequest request);

    UserOrganizationResponse setStatus(UUID userOrganizationId, UserOrganizationStatus status);
}
