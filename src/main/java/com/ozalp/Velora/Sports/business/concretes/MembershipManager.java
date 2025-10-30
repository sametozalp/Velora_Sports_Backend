package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.MembershipService;
import com.ozalp.Velora.Sports.business.abstracts.OrganizationService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateMembershipRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateMembershipResponse;
import com.ozalp.Velora.Sports.business.mappers.MembershipMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.Membership;
import com.ozalp.Velora.Sports.entities.enums.MembershipStatus;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import com.ozalp.Velora.Sports.dataAcess.MembershipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MembershipManager implements MembershipService {

    private final MembershipRepository repository;
    private final MembershipMapper mapper;
    private final OrganizationService organizationService;
    private final UserService userService;

    @Override
    public Membership create(Membership membership) {
        return repository.save(membership);
    }

    @Override
    public Membership findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.MembershipMessages.NOT_FOUND));
    }

    @Override
    public CreateMembershipResponse create(CreateMembershipRequest request) {
        Membership membership = mapper.toEntity(request);
        membership.setMembershipStatus(MembershipStatus.ACTIVE);
        membership.setOrganization(organizationService.findById(UUID.fromString(request.getOrganizationId())));
        membership.setUser(userService.findById(UUID.fromString(request.getUserId())));
        return mapper.toResponse(repository.save(membership));
    }
}
