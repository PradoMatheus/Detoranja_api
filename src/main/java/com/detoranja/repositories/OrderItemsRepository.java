package com.detoranja.repositories;

import com.detoranja.models.OrderItemsModel;
import com.detoranja.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsModel, UUID> {
}
