package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.UserRoleService;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.UserRole;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.dataAcess.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserRoleManager implements UserRoleService {

    private final UserRoleRepository repository;

    @Override
    public UserRole create(UserRole userRole) {
        return repository.save(userRole);
    }

    @Override
    public UserRole findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.UserRoleMessages.NOT_FOUND));
    }
}
