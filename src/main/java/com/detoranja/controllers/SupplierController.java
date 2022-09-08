package com.detoranja.controllers;

import com.detoranja.dtos.SupplierDto;
import com.detoranja.models.SupplierModel;
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
@RequestMapping(value = "/supplier")
public class SupplierController {

    final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Object> saveSupplier(@RequestBody @Valid SupplierDto supplierDto) {
        if (supplierService.existByCnpj(supplierDto.getCnpj()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Cnpj is already in use.");
        var supplierModel = new SupplierModel();
        BeanUtils.copyProperties(supplierDto, supplierModel);
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.save(supplierModel));
    }

    @GetMapping
    public ResponseEntity<List<SupplierModel>> getAllSupplier() {
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneSupplier(@PathVariable(value = "id") UUID id) {
        var supplierModelOptional = supplierService.findById(id);
        if (!supplierModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        return ResponseEntity.status(HttpStatus.OK).body(supplierModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteSupplier(@PathVariable(value = "id") UUID id) {
        var supplierModelOptional = supplierService.findById(id);
        if (!supplierModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        supplierService.delete(supplierModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Supplier deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateSupplier(@RequestBody @Valid SupplierDto supplierDto, @PathVariable(value = "id") UUID id) {
        var supplierModelOptional = supplierService.findById(id);
        if (!supplierModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        var supplierModel = supplierModelOptional.get();
        BeanUtils.copyProperties(supplierDto, supplierModel);
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.save(supplierModel));
    }
}
