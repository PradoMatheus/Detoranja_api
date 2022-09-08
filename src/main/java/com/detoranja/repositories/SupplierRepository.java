package com.detoranja.repositories;

import com.detoranja.models.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel, UUID> {
    boolean existsByCnpj(String cpf);
}
