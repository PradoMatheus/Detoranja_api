package com.detoranja.repositories;

import com.detoranja.models.OrderPaymentModel;
import com.detoranja.models.OrderShippingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderShippingRepository extends JpaRepository<OrderShippingModel, UUID> {
}
