package com.ozalp.Velora.Sports.dataAcess;

import com.ozalp.Velora.Sports.entities.concretes.Role;
import com.ozalp.Velora.Sports.entities.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(RoleEnum name);
}
