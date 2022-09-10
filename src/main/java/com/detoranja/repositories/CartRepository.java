package com.detoranja.repositories;

import com.detoranja.models.CartModel;
import com.detoranja.models.SupplierAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartModel, UUID> {
}
