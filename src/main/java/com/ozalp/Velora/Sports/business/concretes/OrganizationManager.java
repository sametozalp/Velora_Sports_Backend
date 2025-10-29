package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.OrganizationService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateOrganizationResponse;
import com.ozalp.Velora.Sports.business.mappers.OrganizationMapper;
import com.ozalp.Velora.Sports.entities.concretes.Organization;
import com.ozalp.Velora.Sports.repositories.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationManager implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;

    @Override
    public Organization create(Organization organization) {
        return repository.save(organization);
    }

    @Override
    public CreateOrganizationResponse create(CreateOrganizationRequest request) {
        Organization organization = mapper.toEntity(request);
        return mapper.toResponse(repository.save(organization));
    }
}
