package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.OrganizationService;
import com.ozalp.Velora.Sports.business.abstracts.UserOrganizationService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserOrganizationRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.UserOrganizationResponse;
import com.ozalp.Velora.Sports.business.mappers.UserOrganizationMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.UserOrganizationRepository;
import com.ozalp.Velora.Sports.entities.concretes.Organization;
import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.entities.concretes.UserOrganization;
import com.ozalp.Velora.Sports.entities.enums.UserOrganizationStatus;
import com.ozalp.Velora.Sports.exceptions.errors.UserOrganizationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserOrganizationManager implements UserOrganizationService {

    private final UserOrganizationRepository repository;
    private final UserOrganizationMapper mapper;
    private final UserService userService;
    private final OrganizationService organizationService;

    @Override
    public UserOrganization create(UserOrganization userOrganization) {
        List<UserOrganization> dbList = repository.findByUserAndStatus(userOrganization.getUser(), UserOrganizationStatus.ACCEPTED);
        if (!dbList.isEmpty())
            throw new UserOrganizationException(Messages.UserOrganization.ALREADY_REMEMBER);
        return repository.save(userOrganization);
    }

    @Override
    public UserOrganization findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.UserOrganization.NOT_FOUND));
    }

    @Override
    public UserOrganization save(UserOrganization userOrganization) {
        List<UserOrganization> dbList = repository.findByUserAndStatus(userOrganization.getUser(), UserOrganizationStatus.ACCEPTED);
        if (!dbList.isEmpty())
            throw new UserOrganizationException(Messages.UserOrganization.ALREADY_REMEMBER);
        return repository.save(userOrganization);
    }

    @Override
    public UserOrganizationResponse create(CreateUserOrganizationRequest request) {
        User user = userService.findById(request.getUserId());

        List<UserOrganization> dbList = repository.findByUserAndStatus(user, UserOrganizationStatus.ACCEPTED);
        if (!dbList.isEmpty())
            throw new UserOrganizationException(Messages.UserOrganization.ALREADY_REMEMBER);

        Organization organization = organizationService.findById(request.getOrganizationId());
        UserOrganization userOrganization =
                new UserOrganization(user, organization, UserOrganizationStatus.WAITING);
        return mapper.toResponse(repository.save(userOrganization));
    }
}
