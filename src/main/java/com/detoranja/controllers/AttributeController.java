package com.detoranja.controllers;

import com.detoranja.dtos.AttributeDto;
import com.detoranja.models.AttributeModel;
import com.detoranja.services.AttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/product_attribute")
public class AttributeController {

    final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProductAttribute(@RequestBody @Valid AttributeDto attributeDto) {
        if (attributeService.existByName(attributeDto.getName()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use.");
        var productAttributeModel = new AttributeModel();
        BeanUtils.copyProperties(attributeDto, productAttributeModel);
        return ResponseEntity.status(HttpStatus.OK).body(attributeService.save(productAttributeModel));
    }

    @GetMapping
    public ResponseEntity<List<AttributeModel>> getAllProductAttribute() {
        return ResponseEntity.status(HttpStatus.OK).body(attributeService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneProductAttribute(@PathVariable(value = "id") UUID id) {
        var productAttributeModelOptional = attributeService.findById(id);
        if (!productAttributeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute not found");
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProductAttribute(@PathVariable(value = "id") UUID id) {
        var productAttributeModelOptional = attributeService.findById(id);
        if (!productAttributeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute not found");
        attributeService.delete(productAttributeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product Attribute deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProductAttribute(@RequestBody @Valid AttributeDto attributeDto, @PathVariable(value = "id") UUID id) {
        var productAttributeModelOptional = attributeService.findById(id);
        if (!productAttributeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute not found");
        var productAttributeModel = productAttributeModelOptional.get();
        productAttributeModel.setName(attributeDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(attributeService.save(productAttributeModel));
    }
}
