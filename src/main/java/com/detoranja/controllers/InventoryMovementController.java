package com.detoranja.controllers;

import com.detoranja.dtos.InventoryMovementDto;
import com.detoranja.models.InventoryMovementModel;
import com.detoranja.services.InventoryMovementService;
import com.detoranja.services.InventoryService;
import com.detoranja.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/inventory/movement")
public class InventoryMovementController {

    final InventoryMovementService inventoryMovementService;
    final InventoryService inventoryService;
    final ProductService productService;

    public InventoryMovementController(InventoryMovementService inventoryMovementService, InventoryService inventoryService, ProductService productService) {
        this.inventoryMovementService = inventoryMovementService;
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> saveInventoryMovement(@RequestBody @Valid InventoryMovementDto inventoryMovementDto) {
        if (inventoryService.findById(inventoryMovementDto.getInventoryModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Inventory not found");
        var inventoryMovementModel = new InventoryMovementModel();
        BeanUtils.copyProperties(inventoryMovementDto, inventoryMovementModel);
        inventoryMovementModel.setDate_movement(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryMovementService.save(inventoryMovementModel));
    }

    @GetMapping
    public ResponseEntity<List<InventoryMovementModel>> getAllInventoryMovement() {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryMovementService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneInventoryMovement(@PathVariable(value = "id") UUID id) {
        var inventoryMovementModelOptional = inventoryMovementService.findById(id);
        if (!inventoryMovementModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory Movement not found.");
        return ResponseEntity.status(HttpStatus.OK).body(inventoryMovementModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteInventoryMovement(@PathVariable(value = "id") UUID id) {
        var inventoryMovementModelOptional = inventoryMovementService.findById(id);
        if (!inventoryMovementModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory Movement not found.");
        inventoryMovementService.delete(inventoryMovementModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Inventory deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateInventoryMovement(@RequestBody @Valid InventoryMovementDto inventoryMovementDto,
                                                          @PathVariable(value = "id") UUID id) {
        var inventoryMovementModelOptional = inventoryMovementService.findById(id);
        if (!inventoryMovementModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory Movement not found.");
        else if (inventoryService.findById(inventoryMovementDto.getInventoryModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Inventory not found");
        var inventoryMovementModel = inventoryMovementModelOptional.get();
        BeanUtils.copyProperties(inventoryMovementDto, inventoryMovementModel);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryMovementService.save(inventoryMovementModel));
    }
}
