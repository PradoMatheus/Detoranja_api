package com.detoranja.repositories;

import com.detoranja.models.CouponModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<CouponModel, UUID> {
    boolean existsByName(String name);
}
