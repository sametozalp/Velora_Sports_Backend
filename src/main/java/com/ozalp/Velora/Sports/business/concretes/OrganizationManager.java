package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.OrganizationService;
import com.ozalp.Velora.Sports.entities.concretes.Organization;
import com.ozalp.Velora.Sports.repositories.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationManager implements OrganizationService {

    private final OrganizationRepository repository;

    @Override
    public Organization create(Organization organization) {
        return repository.save(organization);
    }
}
