package com.detoranja.controllers;

import com.detoranja.dtos.ExchangeDto;
import com.detoranja.models.ExchangeModel;
import com.detoranja.services.ExchangeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/exchange")
public class ExchangeController {

    final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping
    public ResponseEntity<Object> saveExchange(@RequestBody @Valid ExchangeDto exchangeDto) {
        var exchangeModel = new ExchangeModel();
        BeanUtils.copyProperties(exchangeDto, exchangeModel);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.save(exchangeModel));
    }

    @GetMapping
    public ResponseEntity<List<ExchangeModel>> getAllExchange() {
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneExchange(@PathVariable(value = "id") UUID id) {
        var exchangeModelOptional = exchangeService.findById(id);
        if (!exchangeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange not found");
        return ResponseEntity.status(HttpStatus.OK).body(exchangeModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteExchange(@PathVariable(value = "id") UUID id) {
        var exchangeModelOptional = exchangeService.findById(id);
        if (!exchangeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange not found");
        exchangeService.delete(exchangeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Exchange deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateExchange(@RequestBody @Valid ExchangeDto exchangeDto, @PathVariable(value = "id") UUID id) {
        var exchangeModelOptional = exchangeService.findById(id);
        if (!exchangeModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange not found");
        var exchangeModel = exchangeModelOptional.get();
        BeanUtils.copyProperties(exchangeDto, exchangeModel);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.save(exchangeModel));
    }
}
