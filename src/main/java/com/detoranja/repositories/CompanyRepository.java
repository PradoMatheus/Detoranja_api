package com.detoranja.repositories;

import com.detoranja.models.ClientModel;
import com.detoranja.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, UUID> {
    boolean existsByCnpj(long cnpj);
}
