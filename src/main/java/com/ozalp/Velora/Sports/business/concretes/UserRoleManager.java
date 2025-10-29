package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.UserRoleService;
import com.ozalp.Velora.Sports.entities.concretes.UserRole;
import com.ozalp.Velora.Sports.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRoleManager implements UserRoleService {

    private final UserRoleRepository repository;

    @Override
    public UserRole create(UserRole userRole) {
        return repository.save(userRole);
    }
}
