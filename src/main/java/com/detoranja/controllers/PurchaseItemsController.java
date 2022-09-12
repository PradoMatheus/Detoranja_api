package com.detoranja.controllers;

import com.detoranja.dtos.ExchangeDto;
import com.detoranja.dtos.PurchaseItemsDto;
import com.detoranja.models.ExchangeModel;
import com.detoranja.models.PurchaseItemsModel;
import com.detoranja.services.PurchaseItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/purchase/items")
public class PurchaseItemsController {

    final PurchaseItemsService purchaseItemsService;

    public PurchaseItemsController(PurchaseItemsService purchaseItemsService) {
        this.purchaseItemsService = purchaseItemsService;
    }

    @PostMapping
    public ResponseEntity<Object> savePurchaseItems(@RequestBody @Valid PurchaseItemsDto purchaseItemsDto) {
        var purchaseItemsModel = new PurchaseItemsModel();
        BeanUtils.copyProperties(purchaseItemsDto, purchaseItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseItemsService.save(purchaseItemsModel));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseItemsModel>> getAllPurchaseItems() {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseItemsService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOnePurchaseItems(@PathVariable(value = "id") UUID id) {
        var purchaseItemsModelOptional = purchaseItemsService.findById(id);
        if (!purchaseItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase Items not found");
        return ResponseEntity.status(HttpStatus.OK).body(purchaseItemsModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePurchaseItems(@PathVariable(value = "id") UUID id) {
        var purchaseItemsModelOptional = purchaseItemsService.findById(id);
        if (!purchaseItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase Items not found");
        purchaseItemsService.delete(purchaseItemsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Purchase Items deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updatePurchaseItems(@RequestBody @Valid PurchaseItemsDto purchaseItemsDto, @PathVariable(value = "id") UUID id) {
        var purchaseItemsModelOptional = purchaseItemsService.findById(id);
        if (!purchaseItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase Items not found");
        var purchaseItemsModel = purchaseItemsModelOptional.get();
        BeanUtils.copyProperties(purchaseItemsDto, purchaseItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseItemsService.save(purchaseItemsModel));
    }
}
