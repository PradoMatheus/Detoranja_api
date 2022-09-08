package com.detoranja.controllers;

import com.detoranja.dtos.InventoryDto;
import com.detoranja.dtos.ProductDto;
import com.detoranja.models.InventoryModel;
import com.detoranja.models.ProductModel;
import com.detoranja.services.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/inventory")
public class InventoryController {

    final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Object> saveInventory(@RequestBody @Valid InventoryDto inventoryDto) {
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
        var inventoryModel = inventoryModelOptional.get();
        BeanUtils.copyProperties(inventoryDto, inventoryModel);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.save(inventoryModel));
    }
}
