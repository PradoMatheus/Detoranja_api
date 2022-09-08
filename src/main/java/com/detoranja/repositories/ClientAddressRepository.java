package com.detoranja.repositories;

import com.detoranja.models.ClientAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientAddressRepository extends JpaRepository<ClientAddressModel, UUID> {
}
