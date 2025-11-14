package com.ozalp.Velora.Sports.aop;

import com.ozalp.Velora.Sports.business.abstracts.*;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutItemRequest;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutProgramRequest;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.*;
import com.ozalp.Velora.Sports.entities.enums.UserOrganizationStatus;
import com.ozalp.Velora.Sports.exceptions.errors.AuthorizationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class SecurityAspect {

    private final AthleteProgressService athleteProgressService;
    private final UserService userService;
    private final AthleteService athleteService;
    private final CoachService coachService;
    private final WorkoutItemService workoutItemService;
    private final UserOrganizationService userOrganizationService;

    @Before("@annotation(CheckAthleteOwnership) && args(athleteId, athleteProgressId,..)")
    public void checkAthleteOwnership(UUID athleteId, UUID athleteProgressId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String securityEmail = authentication.getName();

        AthleteProgress progress = athleteProgressService.findById(athleteProgressId);

        if (!securityEmail.equals(progress.getAthlete().getUser().getEmail())) {
            throw new AuthorizationException(Messages.AthleteProgress.NOT_MATCHED);
        }
    }

    @Before("@annotation(CheckCoachOwnerShip) && args(request,..)")
    public void checkCoachOwnerShip(CreateWorkoutItemRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String securityEmail = authentication.getName();
        User authUser = userService.findByEmail(securityEmail);
        Coach authCoachUser = authUser.getCoach();
        if (authCoachUser == null) {
            throw new EntityNotFoundException(Messages.CoachMessages.NOT_FOUND);
        }

        Athlete athlete = athleteService.findById(request.getAthleteId());
        if (!athlete.getCoach().getId().equals(authCoachUser.getId())) {
            throw new AuthorizationException(Messages.AthleteProgress.NOT_MATCHED);
        }
    }

    @Before("@annotation(CheckCoachOwnerShip) && args(request,..)")
    public void checkCoachOwnerShip(CreateWorkoutProgramRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String securityEmail = authentication.getName();
        User authUser = userService.findByEmail(securityEmail);
        Coach authCoachUser = authUser.getCoach();
        if (authCoachUser == null) {
            throw new EntityNotFoundException(Messages.CoachMessages.NOT_FOUND);
        }

        Athlete athlete = athleteService.findById(request.getAthleteId());
        if (!athlete.getCoach().getId().equals(authCoachUser.getId())) {
            throw new AuthorizationException(Messages.AthleteProgress.NOT_MATCHED);
        }
    }

    // TETİKLENMİYOR İNCELENECEK
    @Before("@annotation(CheckOrganizationOwnership) && args(userOrganizationId, status,..)")
    public void checkOrganizationOwnership(UUID userOrganizationId, UserOrganizationStatus status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String securityEmail = authentication.getName();
        User organizationUser = userService.findByEmail(securityEmail);
        UserOrganization owner = userOrganizationService.getAssociateOrganizationOfUser(organizationUser, UserOrganizationStatus.ACCEPTED);

        UserOrganization userOrganization = userOrganizationService.findById(userOrganizationId);

        if (!owner.getOrganization().getId().equals(userOrganization.getOrganization().getId())) {
            throw new AuthorizationException(Messages.UserOrganization.NOT_MATCHED);
        }
    }


}
