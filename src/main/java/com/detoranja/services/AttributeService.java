package com.detoranja.services;

import com.detoranja.models.AttributeModel;
import com.detoranja.repositories.AttributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttributeService {

    final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public boolean existByName(String name) {
        return attributeRepository.existsByName(name);
    }

    public AttributeModel save(AttributeModel attributeModel) {
        return  attributeRepository.save(attributeModel);
    }

    public List<AttributeModel> findAll() {
        return attributeRepository.findAll();
    }

    public Optional<AttributeModel> findById(UUID id) {
        return attributeRepository.findById(id);
    }

    public void delete(AttributeModel attributeModel) {
        attributeRepository.delete(attributeModel);
    }
}
