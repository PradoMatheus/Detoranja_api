package com.detoranja.services;

import com.detoranja.models.SupplierModel;
import com.detoranja.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {

    final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public boolean existByCnpj(String cnpj) {
        return supplierRepository.existsByCnpj(cnpj);
    }

    public SupplierModel save(SupplierModel supplierModel) {
        return supplierRepository.save(supplierModel);
    }

    public List<SupplierModel> findAll() {
        return supplierRepository.findAll();
    }

    public Optional<SupplierModel> findById(UUID id) {
        return supplierRepository.findById(id);
    }

    public void delete(SupplierModel supplierModel) {
        supplierRepository.delete(supplierModel);
    }
}
