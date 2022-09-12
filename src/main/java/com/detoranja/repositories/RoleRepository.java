package com.detoranja.repositories;

import com.detoranja.models.PurchaseModel;
import com.detoranja.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
}
