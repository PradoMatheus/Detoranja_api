package com.detoranja.repositories;

import com.detoranja.models.OrderLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLogModel, UUID> {
}
