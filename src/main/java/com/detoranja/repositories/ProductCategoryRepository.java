package com.detoranja.repositories;

import com.detoranja.models.ProductCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryModel, UUID> {
    boolean existsByName(String name);
}
