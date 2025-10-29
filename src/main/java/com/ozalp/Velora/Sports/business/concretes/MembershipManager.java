package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.MembershipService;
import com.ozalp.Velora.Sports.entities.concretes.Membership;
import com.ozalp.Velora.Sports.repositories.MembershipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MembershipManager implements MembershipService {

    private final MembershipRepository repository;

    @Override
    public Membership create(Membership membership) {
        return repository.save(membership);
    }
}
