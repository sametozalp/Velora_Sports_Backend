package com.ozalp.Velora.Sports.aop;

import com.ozalp.Velora.Sports.business.abstracts.AthleteProgressService;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.entities.concretes.AthleteProgress;
import com.ozalp.Velora.Sports.exceptions.errors.AuthorizationException;
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

    @Before("@annotation(CheckAthleteOwnership) && args(athleteId, athleteProgressId,..)")
    public void checkOwnership(UUID athleteId, UUID athleteProgressId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String securityEmail = authentication.getName();

//        if (authentication.getAuthorities().stream()
//                .anyMatch(auth ->
//                        auth.getAuthority().equals(RoleEnum.ROLE_SUPER_ADMIN.toString()) ||
//                                auth.getAuthority().equals(RoleEnum.ROLE_COACH.toString()) ||
//                                auth.getAuthority().equals(RoleEnum.ROLE_SUPER_ADMIN.toString()))) {
//            return;
//        }

        AthleteProgress progress = athleteProgressService.findById(athleteProgressId);

        if (!securityEmail.equals(progress.getAthlete().getUser().getEmail())) {
            throw new AuthorizationException(Messages.AthleteProgress.NOT_MATCHED);
        }
    }
}
