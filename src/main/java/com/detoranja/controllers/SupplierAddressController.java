package com.detoranja.controllers;

import com.detoranja.dtos.ClientAddressDto;
import com.detoranja.dtos.SupplierAddressDto;
import com.detoranja.models.SupplierAddressModel;
import com.detoranja.services.SupplierAddressService;
import com.detoranja.services.SupplierService;
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
    final SupplierService supplierService;

    public SupplierAddressController(SupplierAddressService supplierAddressService, SupplierService supplierService) {
        this.supplierAddressService = supplierAddressService;
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Object> saveSupplierAddress(@RequestBody @Valid SupplierAddressDto supplierAddressDto) {
        if (supplierService.findById(supplierAddressDto.getSupplierModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Supplier not found");
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
    public ResponseEntity<Object> updateClientAddress(@RequestBody @Valid SupplierAddressDto supplierAddressDto, @PathVariable(value = "id") UUID id) {
        var supplierAddressModelOptional = supplierAddressService.findById(id);
        if (!supplierAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier Address not found");
        else if (supplierService.findById(supplierAddressDto.getSupplierModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Supplier not found");
        var supplierAddressModel = supplierAddressModelOptional.get();
        BeanUtils.copyProperties(supplierAddressDto, supplierAddressModel);
        return ResponseEntity.status(HttpStatus.OK).body(supplierAddressService.save(supplierAddressModel));
    }
}
