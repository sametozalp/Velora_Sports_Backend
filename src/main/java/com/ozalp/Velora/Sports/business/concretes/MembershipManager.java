package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.MembershipService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateMembershipRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateMembershipResponse;
import com.ozalp.Velora.Sports.business.mappers.MembershipMapper;
import com.ozalp.Velora.Sports.entities.concretes.Membership;
import com.ozalp.Velora.Sports.entities.enums.MembershipStatus;
import com.ozalp.Velora.Sports.repositories.MembershipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MembershipManager implements MembershipService {

    private final MembershipRepository repository;
    private final MembershipMapper mapper;

    @Override
    public Membership create(Membership membership) {
        return repository.save(membership);
    }

    @Override
    public CreateMembershipResponse create(CreateMembershipRequest request) {
        Membership membership = mapper.toEntity(request);
        membership.setMembershipStatus(MembershipStatus.ACTIVE);
        return mapper.toResponse(repository.save(membership));
    }
}
