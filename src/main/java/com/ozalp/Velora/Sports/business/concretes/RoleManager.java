package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.RoleService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateRoleRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateRoleResponse;
import com.ozalp.Velora.Sports.business.mappers.RoleMapper;
import com.ozalp.Velora.Sports.entities.concretes.Role;
import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import com.ozalp.Velora.Sports.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public CreateRoleResponse create(CreateRoleRequest request) {
        return mapper.toResponse(mapper.toEntity(request));
    }

    @Override
    public CreateRoleResponse create(RoleEnum request) {
        Role role = new Role(request);
        return mapper.toResponse(repository.save(role));
    }
}
