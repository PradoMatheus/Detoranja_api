package com.detoranja.services;

import com.detoranja.models.InventoryModel;
import com.detoranja.models.InventoryMovementModel;
import com.detoranja.repositories.InventoryMovementRepository;
import com.detoranja.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryMovementService {

    final InventoryMovementRepository inventoryMovementRepository;

    public InventoryMovementService(InventoryMovementRepository inventoryMovementRepository) {
        this.inventoryMovementRepository = inventoryMovementRepository;
    }

    public InventoryMovementModel save(InventoryMovementModel inventoryMovementModel) {
        return inventoryMovementRepository.save(inventoryMovementModel);
    }

    public List<InventoryMovementModel> findAll() {
        return inventoryMovementRepository.findAll();
    }

    public Optional<InventoryMovementModel> findById(UUID id) {
        return inventoryMovementRepository.findById(id);
    }

    public void delete(InventoryMovementModel inventoryMovementModel) {
        inventoryMovementRepository.delete(inventoryMovementModel);
    }
}
