package com.detoranja.repositories;

import com.detoranja.models.InventoryModel;
import com.detoranja.models.InventoryMovementModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovementModel, UUID> {
}
