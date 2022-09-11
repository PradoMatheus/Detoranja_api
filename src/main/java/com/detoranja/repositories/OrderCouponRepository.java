package com.detoranja.repositories;

import com.detoranja.models.OrderCouponModel;
import com.detoranja.models.OrderItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderCouponRepository extends JpaRepository<OrderCouponModel, UUID> {
}
