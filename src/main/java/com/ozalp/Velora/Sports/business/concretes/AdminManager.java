package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AdminService;
import com.ozalp.Velora.Sports.entities.concretes.Admin;
import com.ozalp.Velora.Sports.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminManager implements AdminService {

    private final AdminRepository repository;

    @Override
    public Admin create(Admin admin) {
        return repository.save(admin);
    }
}
