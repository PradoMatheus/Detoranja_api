package com.detoranja.repositories;

import com.detoranja.models.ClientAddressModel;
import com.detoranja.models.ClientContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientContactRepository extends JpaRepository<ClientContactModel, UUID> {
}
