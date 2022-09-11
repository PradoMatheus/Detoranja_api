package com.detoranja.controllers;

import com.detoranja.dtos.CompanyDto;
import com.detoranja.dtos.SupplierDto;
import com.detoranja.models.CompanyModel;
import com.detoranja.models.SupplierModel;
import com.detoranja.services.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/company")
public class CompanyController {

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCompany(@RequestBody @Valid CompanyDto companyDto) {
        if (companyService.existByCnpj(companyDto.getCnpj()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Cnpj is already in use.");
        var companyModel = new CompanyModel();
        BeanUtils.copyProperties(companyDto, companyModel);
        return ResponseEntity.status(HttpStatus.OK).body(companyService.save(companyModel));
    }

    @GetMapping
    public ResponseEntity<List<CompanyModel>> getAllCompany() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneCompany(@PathVariable(value = "id") UUID id) {
        var companyModelOptional = companyService.findById(id);
        if (!companyModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        return ResponseEntity.status(HttpStatus.OK).body(companyModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable(value = "id") UUID id) {
        var companyModelOptional = companyService.findById(id);
        if (!companyModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        companyService.delete(companyModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Company deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateCompany(@RequestBody @Valid CompanyDto companyDto, @PathVariable(value = "id") UUID id) {
        var companyModelOptional = companyService.findById(id);
        if (!companyModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        var companyModel = companyModelOptional.get();
        BeanUtils.copyProperties(companyDto, companyModel);
        return ResponseEntity.status(HttpStatus.OK).body(companyService.save(companyModel));
    }
}
