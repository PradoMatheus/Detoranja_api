package com.detoranja.controllers;

import com.detoranja.dtos.ProductAttributeDto;
import com.detoranja.dtos.ProductAttributeValueDto;
import com.detoranja.models.ProductAttributeModel;
import com.detoranja.models.ProductAttributeValueModel;
import com.detoranja.services.ProductAttributeValueService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/product/attribute/value")
public class ProductAttributeValueController {

    final ProductAttributeValueService productAttributeValueService;

    public ProductAttributeValueController(ProductAttributeValueService productAttributeValueService) {
        this.productAttributeValueService = productAttributeValueService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProductAttributeValue(@RequestBody @Valid ProductAttributeValueDto productAttributeValueDto) {
        var productAttributeValueModel = new ProductAttributeValueModel();
        BeanUtils.copyProperties(productAttributeValueDto, productAttributeValueModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productAttributeValueService.save(productAttributeValueModel));
    }

    @GetMapping
    public ResponseEntity<List<ProductAttributeValueModel>> getAllProductAttributeValue() {
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeValueService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneProductAttribute(@PathVariable(value = "id") UUID id) {
        var productAttributeValueModelOptional = productAttributeValueService.findById(id);
        if (!productAttributeValueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute Value not found.");
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeValueModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProductAttribute(@PathVariable(value = "id") UUID id) {
        var productAttributeValueModelOptional = productAttributeValueService.findById(id);
        if (!productAttributeValueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute Value not found.");
        productAttributeValueService.delete(productAttributeValueModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product Attribute deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProductAttribute(@RequestBody @Valid ProductAttributeValueDto productAttributeValueDto,
                                                         @PathVariable(value = "id") UUID id) {
        var productAttributeValueModelOptional = productAttributeValueService.findById(id);
        if (!productAttributeValueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute Valuenot found.");
        var productAttributeValueModel = productAttributeValueModelOptional.get();
        BeanUtils.copyProperties(productAttributeValueDto, productAttributeValueModel);
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeValueService.save(productAttributeValueModel));
    }
}
