package com.detoranja.repositories;

import com.detoranja.models.ClientContactModel;
import com.detoranja.models.SupplierContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierContactRepository extends JpaRepository<SupplierContactModel, UUID> {
}
