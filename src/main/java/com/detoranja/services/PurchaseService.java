package com.detoranja.services;

import com.detoranja.models.CartModel;
import com.detoranja.models.PurchaseModel;
import com.detoranja.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {

    final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseModel save(PurchaseModel purchaseModel) {
        return purchaseRepository.save(purchaseModel);
    }

    public List<PurchaseModel> findAll() {
        return purchaseRepository.findAll();
    }

    public Optional<PurchaseModel> findById(UUID id) {
        return purchaseRepository.findById(id);
    }

    public void delete(PurchaseModel purchaseModel) {
        purchaseRepository.delete(purchaseModel);
    }
}
