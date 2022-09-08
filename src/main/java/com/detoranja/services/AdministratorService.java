package com.detoranja.services;

import com.detoranja.models.AdministratorModel;
import com.detoranja.repositories.AdministratorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdministratorService {

    final AdministratorRepository administratorRepository;

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Transactional
    public AdministratorModel save(AdministratorModel administratorModel) {
        return administratorRepository.save(administratorModel);
    }

    public boolean existByEmail(String email) {
        return administratorRepository.existsByEmail(email);
    }

    public List<AdministratorModel> findAll() {
        return administratorRepository.findAll();
    }

    public Optional<AdministratorModel> findById(UUID id) {
        return administratorRepository.findById(id);
    }

    @Transactional
    public void delete(AdministratorModel administratorModel) {
        administratorRepository.delete(administratorModel);
    }
}
