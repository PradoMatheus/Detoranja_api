package com.detoranja.repositories;

import com.detoranja.models.ProductAttributeModel;
import com.detoranja.models.ProductAttributeValueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValueModel, UUID> {
}
