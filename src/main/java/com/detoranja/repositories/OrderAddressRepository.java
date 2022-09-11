package com.detoranja.repositories;

import com.detoranja.models.OrderAddressModel;
import com.detoranja.models.OrderCouponModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderAddressRepository extends JpaRepository<OrderAddressModel, UUID> {
}
