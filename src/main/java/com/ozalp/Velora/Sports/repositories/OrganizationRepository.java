package com.ozalp.Velora.Sports.repositories;

import com.ozalp.Velora.Sports.entities.concretes.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
}
