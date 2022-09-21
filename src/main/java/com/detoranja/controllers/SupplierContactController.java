package com.detoranja.controllers;

import com.detoranja.dtos.SupplierContactDto;
import com.detoranja.models.SupplierContactModel;
import com.detoranja.services.SupplierContactService;
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
@RequestMapping(value = "/supplier/contact")
public class SupplierContactController {

    final SupplierContactService supplierContactService;
    final SupplierService supplierService;

    public SupplierContactController(SupplierContactService supplierContactService, SupplierService supplierService) {
        this.supplierContactService = supplierContactService;
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Object> saveSupplierContact(@RequestBody @Valid SupplierContactDto supplierContactDto) {
        if (supplierService.findById(supplierContactDto.getSupplierModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Supplier not found");
        var supplierContactModel = new SupplierContactModel();
        BeanUtils.copyProperties(supplierContactDto, supplierContactModel);
        return ResponseEntity.status(HttpStatus.OK).body(supplierContactService.save(supplierContactModel));
    }

    @GetMapping
    public ResponseEntity<List<SupplierContactModel>> getAllSupplierContact() {
        return ResponseEntity.status(HttpStatus.OK).body(supplierContactService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneSupplierContact(@PathVariable(value = "id") UUID id) {
        var supplierContactModelOptional = supplierContactService.findById(id);
        if (!supplierContactModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier Contact not found");
        return ResponseEntity.status(HttpStatus.OK).body(supplierContactModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteSupplierContact(@PathVariable(value = "id") UUID id) {
        var supplierContactModelOptional = supplierContactService.findById(id);
        if (!supplierContactModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier Contact not found");
        supplierContactService.delete(supplierContactModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Supplier Contact deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateSupplierContact(@RequestBody @Valid SupplierContactDto supplierContactDto, @PathVariable(value = "id") UUID id) {
        var supplierContactModelOptional = supplierContactService.findById(id);
        if (!supplierContactModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier Contact not found");
        else if (supplierService.findById(supplierContactDto.getSupplierModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Supplier not found");
        var clientContactModel = supplierContactModelOptional.get();
        BeanUtils.copyProperties(supplierContactDto, clientContactModel);
        return ResponseEntity.status(HttpStatus.OK).body(supplierContactService.save(clientContactModel));
    }
}
