package com.detoranja.services;

import com.detoranja.models.ProductCategoryModel;
import com.detoranja.repositories.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductCategoryService {

    final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public ProductCategoryModel save(ProductCategoryModel productCategoryModel) {
        return productCategoryRepository.save(productCategoryModel);
    }

    public boolean existsByName(String name) {
        return productCategoryRepository.existsByName(name);
    }

    public List<ProductCategoryModel> findAll() {
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategoryModel> findById(UUID id) {
        return productCategoryRepository.findById(id);
    }

    public void delete(ProductCategoryModel productCategoryModel) {
        productCategoryRepository.delete(productCategoryModel);
    }
}
