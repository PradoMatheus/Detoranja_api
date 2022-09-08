package com.detoranja.repositories;

import com.detoranja.models.AttributeValueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValueModel, UUID> {
    boolean existsByName(String name);
}
