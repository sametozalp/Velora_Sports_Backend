package com.ozalp.Velora.Sports.dataAcess;

import com.ozalp.Velora.Sports.entities.concretes.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
