package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.OrganizationService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateOrganizationResponse;
import com.ozalp.Velora.Sports.business.mappers.OrganizationMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.OrganizationRepository;
import com.ozalp.Velora.Sports.entities.concretes.Organization;
import com.ozalp.Velora.Sports.entities.enums.OrganizationStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrganizationManager implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;

    @Override
    public Organization create(Organization organization) {
        organization.setStatus(OrganizationStatus.ACTIVE);
        return repository.save(organization);
    }

    @Override
    public Organization findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.OrganizationMessages.NOT_FOUND));
    }

    @Override
    public Organization save(Organization organization) {
        organization.setStatus(OrganizationStatus.ACTIVE);
        return repository.save(organization);
    }

    @Override
    public CreateOrganizationResponse create(CreateOrganizationRequest request) {
        Organization organization = mapper.toEntity(request);
        organization.setStatus(OrganizationStatus.ACTIVE);
        return mapper.toResponse(repository.save(organization));
    }

    @Override
    public List<CreateOrganizationResponse> getActiveOrganizations() {
        return repository.findByStatus(OrganizationStatus.ACTIVE)
                .stream().map(mapper::toResponse)
                .toList();
    }
}
