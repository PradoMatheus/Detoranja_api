package com.detoranja.controllers;

import com.detoranja.dtos.ClientAddressDto;
import com.detoranja.dtos.SupplierAddressDto;
import com.detoranja.models.ClientAddressModel;
import com.detoranja.models.SupplierAddressModel;
import com.detoranja.services.ClientAddressService;
import com.detoranja.services.SupplierAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/supplier/address")
public class SupplierAddressController {

    final SupplierAddressService supplierAddressService;

    public SupplierAddressController(SupplierAddressService supplierAddressService) {
        this.supplierAddressService = supplierAddressService;
    }

    @PostMapping
    public ResponseEntity<Object> saveSupplierAddress(@RequestBody @Valid SupplierAddressDto supplierAddressDto) {
        var supplierAddressModel = new SupplierAddressModel();
        BeanUtils.copyProperties(supplierAddressDto, supplierAddressModel);
        return ResponseEntity.status(HttpStatus.OK).body(supplierAddressService.save(supplierAddressModel));
    }

    @GetMapping
    public ResponseEntity<List<SupplierAddressModel>> getAllSupplierAddress() {
        return ResponseEntity.status(HttpStatus.OK).body(supplierAddressService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneSupplierAddress(@PathVariable(value = "id") UUID id) {
        var supplierAddressModelOptional = supplierAddressService.findById(id);
        if (!supplierAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier Address not found");
        return ResponseEntity.status(HttpStatus.OK).body(supplierAddressModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteSupplierAddress(@PathVariable(value = "id") UUID id) {
        var supplierAddressModelOptional = supplierAddressService.findById(id);
        if (!supplierAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier Address not found");
        supplierAddressService.delete(supplierAddressModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Supplier Address deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateClientAddress(@RequestBody @Valid ClientAddressDto clientAddressDto, @PathVariable(value = "id") UUID id) {
        var supplierAddressModelOptional = supplierAddressService.findById(id);
        if (!supplierAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier Address not found");
        var clientAddressModel = supplierAddressModelOptional.get();
        BeanUtils.copyProperties(clientAddressDto, clientAddressModel);
        return ResponseEntity.status(HttpStatus.OK).body(supplierAddressService.save(clientAddressModel));
    }
}
