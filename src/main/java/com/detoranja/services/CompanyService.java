package com.detoranja.services;

import com.detoranja.models.CartModel;
import com.detoranja.models.CompanyModel;
import com.detoranja.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyModel save(CompanyModel companyModel) {
        return companyRepository.save(companyModel);
    }

    public List<CompanyModel> findAll() {
        return companyRepository.findAll();
    }

    public Optional<CompanyModel> findById(UUID id) {
        return companyRepository.findById(id);
    }

    public void delete(CompanyModel companyModel) {
        companyRepository.delete(companyModel);
    }

    public boolean existByCnpj(long cnpj) {
        return companyRepository.existsByCnpj(cnpj);
    }
}
