package com.ozalp.Velora.Sports.common;

import com.ozalp.Velora.Sports.dataAcess.RoleRepository;
import com.ozalp.Velora.Sports.entities.concretes.Role;
import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    ApplicationRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            Role athlete = roleRepository.findByName(RoleEnum.ROLE_ATHLETE);
            Role coach = roleRepository.findByName(RoleEnum.ROLE_COACH);
            Role orgAdmin = roleRepository.findByName(RoleEnum.ROLE_ORG_ADMIN);
            Role orgSubAdmin = roleRepository.findByName(RoleEnum.ROLE_ORG_SUB_ADMIN);
            Role superAdmin = roleRepository.findByName(RoleEnum.ROLE_SUPER_ADMIN);

            if (athlete == null) {
                roleRepository.save(new Role(RoleEnum.ROLE_ATHLETE));
            }

            if (coach == null) {
                roleRepository.save(new Role(RoleEnum.ROLE_COACH));
            }

            if (orgAdmin == null) {
                roleRepository.save(new Role(RoleEnum.ROLE_ORG_ADMIN));
            }

            if (orgSubAdmin == null) {
                roleRepository.save(new Role(RoleEnum.ROLE_ORG_SUB_ADMIN));
            }

            if (superAdmin == null) {
                roleRepository.save(new Role(RoleEnum.ROLE_SUPER_ADMIN));
            }
        };
    }
}
