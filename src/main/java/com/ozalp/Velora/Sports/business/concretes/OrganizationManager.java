package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.OrganizationService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateOrganizationResponse;
import com.ozalp.Velora.Sports.business.mappers.OrganizationMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.Organization;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.dataAcess.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public Organization findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.OrganizationMessages.NOT_FOUND));
    }

    @Override
    public CreateOrganizationResponse create(CreateOrganizationRequest request) {
        Organization organization = mapper.toEntity(request);
        return mapper.toResponse(repository.save(organization));
    }
}
