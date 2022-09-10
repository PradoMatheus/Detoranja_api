package com.detoranja.services;

import com.detoranja.models.ProductAttributeValueModel;
import com.detoranja.repositories.ProductAttributeValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductAttributeValueService {

    final ProductAttributeValueRepository productAttributeValueRepository;

    public ProductAttributeValueService(ProductAttributeValueRepository productAttributeValueRepository) {
        this.productAttributeValueRepository = productAttributeValueRepository;
    }

    public ProductAttributeValueModel save(ProductAttributeValueModel productAttributeValueModel) {
        return productAttributeValueRepository.save(productAttributeValueModel);
    }

    public List<ProductAttributeValueModel> findAll() {
        return productAttributeValueRepository.findAll();
    }

    public Optional<ProductAttributeValueModel> findById(UUID id) {
        return productAttributeValueRepository.findById(id);
    }

    public void delete(ProductAttributeValueModel productAttributeValueModel) {
        productAttributeValueRepository.delete(productAttributeValueModel);
    }
}
