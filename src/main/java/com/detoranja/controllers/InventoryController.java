package com.detoranja.controllers;

import com.detoranja.dtos.InventoryDto;
import com.detoranja.models.InventoryModel;
import com.detoranja.services.InventoryService;
import com.detoranja.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/inventory")
public class InventoryController {

    final InventoryService inventoryService;
    final ProductService productService;

    public InventoryController(InventoryService inventoryService, ProductService productService) {
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> saveInventory(@RequestBody @Valid InventoryDto inventoryDto) {
        if (inventoryService.existsByProductModel(inventoryDto.getProductModel()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Product Inventory just exist.");
        if (productService.findById(inventoryDto.getProductModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Product not found.");
        var inventoryModel = new InventoryModel();
        BeanUtils.copyProperties(inventoryDto, inventoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.save(inventoryModel));
    }

    @GetMapping
    public ResponseEntity<List<InventoryModel>> getAllInventory() {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneInventory(@PathVariable(value = "id") UUID id) {
        var inventoryModelOptional = inventoryService.findById(id);
        if (!inventoryModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory not found.");
        return ResponseEntity.status(HttpStatus.OK).body(inventoryModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteInventory(@PathVariable(value = "id") UUID id) {
        var inventoryModelOptional = inventoryService.findById(id);
        if (!inventoryModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        inventoryService.delete(inventoryModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Inventory deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateInventory(@RequestBody @Valid InventoryDto inventoryDto,
                                                @PathVariable(value = "id") UUID id) {
        var inventoryModelOptional = inventoryService.findById(id);
        if (!inventoryModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory not found.");
        if (productService.findById(inventoryDto.getProductModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Product not found.");
        var inventoryModel = inventoryModelOptional.get();
        BeanUtils.copyProperties(inventoryDto, inventoryModel);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.save(inventoryModel));
    }
}
