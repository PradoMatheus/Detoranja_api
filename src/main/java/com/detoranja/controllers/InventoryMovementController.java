package com.detoranja.controllers;

import com.detoranja.dtos.InventoryDto;
import com.detoranja.dtos.InventoryMovementDto;
import com.detoranja.models.InventoryModel;
import com.detoranja.models.InventoryMovementModel;
import com.detoranja.services.InventoryMovementService;
import com.detoranja.services.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/inventory/movement")
public class InventoryMovementController {

    final InventoryMovementService inventoryMovementService;

    public InventoryMovementController(InventoryMovementService inventoryMovementService) {
        this.inventoryMovementService = inventoryMovementService;
    }

    @PostMapping
    public ResponseEntity<Object> saveInventoryMovement(@RequestBody @Valid InventoryMovementDto inventoryMovementDto) {
        var inventoryMovementModel = new InventoryMovementModel();
        BeanUtils.copyProperties(inventoryMovementDto, inventoryMovementModel);
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
        var inventoryMovementModel = inventoryMovementModelOptional.get();
        BeanUtils.copyProperties(inventoryMovementDto, inventoryMovementModel);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryMovementService.save(inventoryMovementModel));
    }
}
