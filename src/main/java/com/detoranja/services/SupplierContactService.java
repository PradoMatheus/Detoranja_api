package com.detoranja.services;

import com.detoranja.models.ClientContactModel;
import com.detoranja.models.SupplierContactModel;
import com.detoranja.repositories.SupplierContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierContactService {

    final SupplierContactRepository supplierContactRepository;

    public SupplierContactService(SupplierContactRepository supplierContactRepository) {
        this.supplierContactRepository = supplierContactRepository;
    }

    public SupplierContactModel save(SupplierContactModel supplierContactModel) {
        return supplierContactRepository.save(supplierContactModel);
    }

    public List<SupplierContactModel> findAll() {
        return supplierContactRepository.findAll();
    }

    public Optional<SupplierContactModel> findById(UUID id) {
        return supplierContactRepository.findById(id);
    }

    public void delete(SupplierContactModel supplierContactModel) {
        supplierContactRepository.delete(supplierContactModel);
    }
}
