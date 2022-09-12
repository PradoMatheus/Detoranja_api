package com.detoranja.services;

import com.detoranja.models.CartModel;
import com.detoranja.models.PurchaseItemsModel;
import com.detoranja.repositories.PurchaseItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseItemsService {

    final PurchaseItemsRepository purchaseItemsRepository;

    public PurchaseItemsService(PurchaseItemsRepository purchaseItemsRepository) {
        this.purchaseItemsRepository = purchaseItemsRepository;
    }

    public PurchaseItemsModel save(PurchaseItemsModel purchaseItemsModel) {
        return purchaseItemsRepository.save(purchaseItemsModel);
    }

    public List<PurchaseItemsModel> findAll() {
        return purchaseItemsRepository.findAll();
    }

    public Optional<PurchaseItemsModel> findById(UUID id) {
        return purchaseItemsRepository.findById(id);
    }

    public void delete(PurchaseItemsModel purchaseItemsModel) {
        purchaseItemsRepository.delete(purchaseItemsModel);
    }
}
