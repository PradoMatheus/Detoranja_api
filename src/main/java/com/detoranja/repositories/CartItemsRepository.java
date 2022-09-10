package com.detoranja.repositories;

import com.detoranja.models.CartItemsModel;
import com.detoranja.models.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItemsModel, UUID> {
}
