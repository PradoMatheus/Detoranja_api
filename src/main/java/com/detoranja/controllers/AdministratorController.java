package com.detoranja.controllers;

import com.detoranja.dtos.AdministratorDto;
import com.detoranja.models.AdministratorModel;
import com.detoranja.services.AdministratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/administrator")
public class AdministratorController {

    final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAdministrator(@RequestBody @Valid AdministratorDto administratorDto) {
        if (administratorService.existByEmail(administratorDto.getEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: E-mail is already in use.");
        var administratorModel = new AdministratorModel();
        BeanUtils.copyProperties(administratorDto, administratorModel);
        administratorModel.setCreateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(administratorService.save(administratorModel));
    }

    @GetMapping
    public ResponseEntity<List<AdministratorModel>> getAllAdministrator() {
        return ResponseEntity.status(HttpStatus.OK).body(administratorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAdministrator(@PathVariable(value = "id") UUID id) {
        Optional<AdministratorModel> administratorModelOptional = administratorService.findById(id);
        if (!administratorModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrator not found.");
        return ResponseEntity.status(HttpStatus.OK).body(administratorModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdministrator(@PathVariable(value = "id") UUID id) {
        Optional<AdministratorModel> administratorModelOptional = administratorService.findById(id);
        if (!administratorModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrator not found.");
        administratorService.delete(administratorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Administrator deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdministrator(@PathVariable(value = "id") UUID id,
                                                      @RequestBody @Valid AdministratorDto administratorDto) {
        Optional<AdministratorModel> administratorModelOptional = administratorService.findById(id);
        if (!administratorModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrator not found.");
        var administratorModel = new AdministratorModel();
        BeanUtils.copyProperties(administratorDto, administratorModel);
        administratorModel.setId(administratorModelOptional.get().getId());
        administratorModel.setCreateDate(administratorModelOptional.get().getCreateDate());
        return ResponseEntity.status(HttpStatus.OK).body(administratorService.save(administratorModel));
    }
}
