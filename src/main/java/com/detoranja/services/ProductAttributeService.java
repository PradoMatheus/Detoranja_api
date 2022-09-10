package com.detoranja.services;

import com.detoranja.models.ProductAttributeModel;
import com.detoranja.models.ProductModel;
import com.detoranja.repositories.ProductAttributeRepository;
import com.detoranja.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductAttributeService {

    final ProductAttributeRepository productAttributeRepository;

    public ProductAttributeService(ProductAttributeRepository productAttributeRepository) {
        this.productAttributeRepository = productAttributeRepository;
    }

    public ProductAttributeModel save(ProductAttributeModel productAttributeModel) {
        return productAttributeRepository.save(productAttributeModel);
    }

    public List<ProductAttributeModel> findAll() {
        return productAttributeRepository.findAll();
    }

    public Optional<ProductAttributeModel> findById(UUID id) {
        return productAttributeRepository.findById(id);
    }

    public void delete(ProductAttributeModel productAttributeModel) {
        productAttributeRepository.delete(productAttributeModel);
    }
}
