package com.detoranja.controllers;

import com.detoranja.dtos.PurchaseItemsDto;
import com.detoranja.models.PurchaseItemsModel;
import com.detoranja.services.ProductService;
import com.detoranja.services.PurchaseItemsService;
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
@RequestMapping(value = "/purchase/items")
public class PurchaseItemsController {

    final PurchaseItemsService purchaseItemsService;
    final PurchaseService purchaseService;
    final ProductService productService;

    public PurchaseItemsController(PurchaseItemsService purchaseItemsService, PurchaseService purchaseService, ProductService productService) {
        this.purchaseItemsService = purchaseItemsService;
        this.purchaseService = purchaseService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> savePurchaseItems(@RequestBody @Valid PurchaseItemsDto purchaseItemsDto) {
        if (purchaseService.findById(purchaseItemsDto.getPurchaseModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Purchase not found");
        else if (productService.findById(purchaseItemsDto.getProductModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Product not found");
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
        else if (purchaseService.findById(purchaseItemsDto.getPurchaseModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Purchase not found");
        else if (productService.findById(purchaseItemsDto.getProductModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Product not found");
        var purchaseItemsModel = purchaseItemsModelOptional.get();
        BeanUtils.copyProperties(purchaseItemsDto, purchaseItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseItemsService.save(purchaseItemsModel));
    }
}
