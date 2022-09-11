package com.detoranja.repositories;

import com.detoranja.models.ExchangeItemsModel;
import com.detoranja.models.ExchangeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExchangeItemsRepository extends JpaRepository<ExchangeItemsModel, UUID> {
}
