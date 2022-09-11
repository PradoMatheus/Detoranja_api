package com.detoranja.repositories;

import com.detoranja.models.ExchangeLogModel;
import com.detoranja.models.ExchangeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExchangeLogRepository extends JpaRepository<ExchangeLogModel, UUID> {
}
