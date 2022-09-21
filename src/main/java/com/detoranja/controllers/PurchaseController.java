package com.detoranja.controllers;

import com.detoranja.dtos.PurchaseDto;
import com.detoranja.models.PurchaseModel;
import com.detoranja.services.PurchaseService;
import com.detoranja.services.SupplierService;
import com.detoranja.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/purchase")
public class PurchaseController {

    final PurchaseService purchaseService;
    final SupplierService supplierService;
    final UserService userService;

    public PurchaseController(PurchaseService purchaseService, SupplierService supplierService, UserService userService) {
        this.purchaseService = purchaseService;
        this.supplierService = supplierService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> savePurchase(@RequestBody @Valid PurchaseDto purchaseDto, @AuthenticationPrincipal User user) {
        if (supplierService.findById(purchaseDto.getSupplierModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        var purchaseModel = new PurchaseModel();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        purchaseModel.setCreate_date(LocalDateTime.now(ZoneId.of("UTC")));
        purchaseModel.setUser_model(userService.findByUsername(user.getUsername()));
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
        if (supplierService.findById(purchaseDto.getSupplierModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        var purchaseModel = purchaseModelOptional.get();
        BeanUtils.copyProperties(purchaseDto, purchaseModel);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.save(purchaseModel));
    }
}
