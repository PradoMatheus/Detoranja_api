package com.detoranja.services;

import com.detoranja.models.SupplierAddressModel;
import com.detoranja.repositories.SupplierAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierAddressService {

    final SupplierAddressRepository supplierAddressRepository;

    public SupplierAddressService(SupplierAddressRepository supplierAddressRepository) {
        this.supplierAddressRepository = supplierAddressRepository;
    }

    public SupplierAddressModel save(SupplierAddressModel supplierAddressModel) {
        return supplierAddressRepository.save(supplierAddressModel);
    }

    public List<SupplierAddressModel> findAll() {
        return supplierAddressRepository.findAll();
    }

    public Optional<SupplierAddressModel> findById(UUID id) {
        return supplierAddressRepository.findById(id);
    }

    public void delete(SupplierAddressModel supplierAddressModel) {
        supplierAddressRepository.delete(supplierAddressModel);
    }
}
