package com.detoranja.controllers;

import com.detoranja.dtos.ProductAttributeDto;
import com.detoranja.dtos.ProductDto;
import com.detoranja.models.ProductAttributeModel;
import com.detoranja.models.ProductModel;
import com.detoranja.services.ProductAttributeService;
import com.detoranja.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/product/attribute")
public class ProductAttributeController {

    final ProductAttributeService productAttributeService;

    public ProductAttributeController(ProductAttributeService productAttributeService) {
        this.productAttributeService = productAttributeService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProductAttribute(@RequestBody @Valid ProductAttributeDto productAttributeDto) {
        var productAttributeModel = new ProductAttributeModel();
        BeanUtils.copyProperties(productAttributeDto, productAttributeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productAttributeService.save(productAttributeModel));
    }

    @GetMapping
    public ResponseEntity<List<ProductAttributeModel>> getAllProductAttribute() {
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneProductAttribute(@PathVariable(value = "id") UUID id) {
        var productAttributeModelOptional = productAttributeService.findById(id);
        if (!productAttributeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute not found.");
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProductAttribute(@PathVariable(value = "id") UUID id) {
        var productAttributeModelOptional = productAttributeService.findById(id);
        if (!productAttributeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute not found.");
        productAttributeService.delete(productAttributeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product Attribute deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProductAttribute(@RequestBody @Valid ProductAttributeDto productAttributeDto,
                                                @PathVariable(value = "id") UUID id) {
        var productAttributeModelOptional = productAttributeService.findById(id);
        if (!productAttributeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        var productAttributeModel = productAttributeModelOptional.get();
        BeanUtils.copyProperties(productAttributeDto, productAttributeModel);
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeService.save(productAttributeModel));
    }
}
