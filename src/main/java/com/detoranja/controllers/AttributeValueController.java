package com.detoranja.controllers;

import com.detoranja.dtos.AttributeValueDto;
import com.detoranja.models.AttributeValueModel;
import com.detoranja.services.AttributeService;
import com.detoranja.services.AttributeValueService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/attribute/value")
public class AttributeValueController {

    final AttributeValueService productAttributeValueService;
    final AttributeService attributeService;

    public AttributeValueController(AttributeValueService productAttributeValueService, AttributeService attributeService) {
        this.productAttributeValueService = productAttributeValueService;
        this.attributeService = attributeService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProductAttributeValue(@RequestBody @Valid AttributeValueDto attributeValueDto) {
        if (productAttributeValueService.existByName(attributeValueDto.getName()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use.");
        if (attributeService.findById(attributeValueDto.getAttributeModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attribute not found");
        var productAttributeValueModel = new AttributeValueModel();
        BeanUtils.copyProperties(attributeValueDto, productAttributeValueModel);
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeValueService.save(productAttributeValueModel));
    }

    @GetMapping
    public ResponseEntity<List<AttributeValueModel>> getAllProductAttributeValue() {
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeValueService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProductAttributeValue(@PathVariable(value = "id") UUID id) {
        var attributeValueModelOptional = productAttributeValueService.findById(id);
        if (!attributeValueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute Value not found.");
        return ResponseEntity.status(HttpStatus.OK).body(attributeValueModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductAttributeValue(@PathVariable(value = "id") UUID id) {
        var attributeValueModelOptional = productAttributeValueService.findById(id);
        if (!attributeValueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute Value not found.");
        productAttributeValueService.delete(attributeValueModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product Attribute Value deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductAttributeValue(@RequestBody @Valid AttributeValueDto attributeValueDto,
                                                              @PathVariable(value = "id") UUID id) {
        var attributeValueModelOptional = productAttributeValueService.findById(id);
        if (!attributeValueModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Attribute Value not found.");
        if (attributeService.findById(attributeValueDto.getAttributeModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attribute not found");
        var productAttributeValueModel = attributeValueModelOptional.get();
        productAttributeValueModel.setName(attributeValueDto.getName());
        productAttributeValueModel.setAttributeModel(attributeValueDto.getAttributeModel());
        return ResponseEntity.status(HttpStatus.OK).body(productAttributeValueService.save(productAttributeValueModel));
    }
}
