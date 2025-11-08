package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.RoleService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateRoleRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateRoleResponse;
import com.ozalp.Velora.Sports.business.mappers.RoleMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.RoleRepository;
import com.ozalp.Velora.Sports.entities.concretes.Role;
import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public Role create(Role role) {
        return repository.save(role);
    }

    @Override
    public Role findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.RoleMessages.NOT_FOUND));
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public CreateRoleResponse create(CreateRoleRequest request) {
        return mapper.toResponse(mapper.toEntity(request));
    }

    @Override
    public CreateRoleResponse create(RoleEnum request) {
        Role role = new Role(request);
        return mapper.toResponse(repository.save(role));
    }

    @Override
    public Role findByName(RoleEnum name) {
        return repository.findByName(name);
    }
}
