package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.RoleService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateUserResponse;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.UserRepository;
import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import com.ozalp.Velora.Sports.entities.concretes.Role;
import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.entities.concretes.UserRole;
import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import com.ozalp.Velora.Sports.entities.enums.UserStatus;
import com.ozalp.Velora.Sports.exceptions.errors.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final RoleService roleService;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.UserMessages.NOT_FOUND));
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional
    @Override
    public CreateUserResponse create(CreateUserRequest request) {
        User user = mapper.toEntity(request);
        user.setUserStatus(UserStatus.ACTIVE);
        Athlete athlete = new Athlete();
        athlete.setUser(user);
        Role role = roleService.findByName(RoleEnum.ROLE_ATHLETE);
        if (role == null) {
            role = new Role(RoleEnum.ROLE_ATHLETE);
            role = roleService.save(role);

        }
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        user.setRoles(new HashSet<>(List.of(userRole)));
        user.setAthlete(athlete);
        User saved = repository.save(user);
        return mapper.toResponse(findById(saved.getId()));
    }

}
