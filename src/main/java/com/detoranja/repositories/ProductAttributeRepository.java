package com.detoranja.repositories;

import com.detoranja.models.ProductAttributeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttributeModel, UUID> {
}
