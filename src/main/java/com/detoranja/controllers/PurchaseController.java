package com.detoranja.controllers;

import com.detoranja.dtos.ExchangeDto;
import com.detoranja.dtos.PurchaseDto;
import com.detoranja.models.ExchangeModel;
import com.detoranja.models.PurchaseModel;
import com.detoranja.services.PurchaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/purchase")
public class PurchaseController {

    final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Object> savePurchase(@RequestBody @Valid PurchaseDto purchaseDto) {
        var purchaseModel = new PurchaseModel();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.save(purchaseModel));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseModel>> getAllPurchase() {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOnePurchase(@PathVariable(value = "id") UUID id) {
        var purchaseModelOptional = purchaseService.findById(id);
        if (!purchaseModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found");
        return ResponseEntity.status(HttpStatus.OK).body(purchaseModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePurchase(@PathVariable(value = "id") UUID id) {
        var purchaseModelOptional = purchaseService.findById(id);
        if (!purchaseModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found");
        purchaseService.delete(purchaseModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Purchase deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updatePurchase(@RequestBody @Valid PurchaseDto purchaseDto, @PathVariable(value = "id") UUID id) {
        var purchaseModelOptional = purchaseService.findById(id);
        if (!purchaseModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found");
        var purchaseModel = purchaseModelOptional.get();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.save(purchaseModel));
    }
}
