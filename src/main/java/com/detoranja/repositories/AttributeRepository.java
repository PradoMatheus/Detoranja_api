package com.detoranja.repositories;

import com.detoranja.models.AttributeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeModel, UUID> {
    boolean existsByName(String name);
}
