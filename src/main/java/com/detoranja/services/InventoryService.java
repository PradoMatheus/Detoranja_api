package com.detoranja.services;

import com.detoranja.models.InventoryModel;
import com.detoranja.models.ProductModel;
import com.detoranja.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {

    final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryModel save(InventoryModel inventoryModel) {
        return inventoryRepository.save(inventoryModel);
    }

    public List<InventoryModel> findAll() {
        return inventoryRepository.findAll();
    }

    public Optional<InventoryModel> findById(UUID id) {
        return inventoryRepository.findById(id);
    }

    public void delete(InventoryModel inventoryModel) {
        inventoryRepository.delete(inventoryModel);
    }

    public Boolean existsByProductModel(ProductModel productModel){
        return inventoryRepository.existsByProductModel(productModel);
    }
}
