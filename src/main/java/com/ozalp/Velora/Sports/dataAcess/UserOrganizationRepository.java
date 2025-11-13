package com.ozalp.Velora.Sports.dataAcess;

import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.entities.concretes.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserOrganizationRepository extends JpaRepository<UserOrganization, UUID> {
    List<UserOrganization> findByUserAndDeletedAtIsNull(User user);
}
