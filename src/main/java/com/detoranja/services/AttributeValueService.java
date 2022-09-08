package com.detoranja.services;

import com.detoranja.models.AttributeValueModel;
import com.detoranja.repositories.AttributeValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttributeValueService {

    final AttributeValueRepository attributeValueRepository;

    public AttributeValueService(AttributeValueRepository attributeValueRepository) {
        this.attributeValueRepository = attributeValueRepository;
    }

    public boolean existByName(String name) {
        return attributeValueRepository.existsByName(name);
    }

    public AttributeValueModel save(AttributeValueModel productAttributeValueModel) {
        return attributeValueRepository.save(productAttributeValueModel);
    }

    public List<AttributeValueModel> findAll() {
        return attributeValueRepository.findAll();
    }

    public Optional<AttributeValueModel> findById(UUID id) {
        return attributeValueRepository.findById(id);
    }

    public void delete(AttributeValueModel productAttributeValueModel) {
        attributeValueRepository.delete(productAttributeValueModel);
    }
}
