package com.detoranja.repositories;

import com.detoranja.models.AdministratorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdministratorRepository extends JpaRepository<AdministratorModel, UUID> {
    boolean existsByEmail(String email);
}
