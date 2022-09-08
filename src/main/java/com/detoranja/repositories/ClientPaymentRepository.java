package com.detoranja.repositories;

import com.detoranja.models.ClientAddressModel;
import com.detoranja.models.ClientPaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientPaymentRepository extends JpaRepository<ClientPaymentModel, UUID> {
}
