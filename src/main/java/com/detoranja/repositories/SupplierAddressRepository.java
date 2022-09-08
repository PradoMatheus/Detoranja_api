package com.detoranja.repositories;

import com.detoranja.models.ClientAddressModel;
import com.detoranja.models.SupplierAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierAddressRepository extends JpaRepository<SupplierAddressModel, UUID> {
}
