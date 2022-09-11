package com.detoranja.controllers;

import com.detoranja.dtos.ExchangeDto;
import com.detoranja.dtos.ExchangeItemsDto;
import com.detoranja.models.ExchangeItemsModel;
import com.detoranja.models.ExchangeModel;
import com.detoranja.services.ExchangeItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/exchange/items")
public class ExchangeItemsController {

    final ExchangeItemsService exchangeItemsService;

    public ExchangeItemsController(ExchangeItemsService exchangeItemsService) {
        this.exchangeItemsService = exchangeItemsService;
    }

    @PostMapping
    public ResponseEntity<Object> saveExchangeItems(@RequestBody @Valid ExchangeItemsDto exchangeItemsDto) {
        var exchangeItemsModel = new ExchangeItemsModel();
        BeanUtils.copyProperties(exchangeItemsDto, exchangeItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeItemsService.save(exchangeItemsModel));
    }

    @GetMapping
    public ResponseEntity<List<ExchangeItemsModel>> getAllExchangeItems() {
        return ResponseEntity.status(HttpStatus.OK).body(exchangeItemsService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneExchangeItems(@PathVariable(value = "id") UUID id) {
        var exchangeItemsModelOptional = exchangeItemsService.findById(id);
        if (!exchangeItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Items not found");
        return ResponseEntity.status(HttpStatus.OK).body(exchangeItemsModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteExchangeItems(@PathVariable(value = "id") UUID id) {
        var exchangeItemsModelOptional = exchangeItemsService.findById(id);
        if (!exchangeItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Items not found");
        exchangeItemsService.delete(exchangeItemsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Exchange Items deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateExchangeItems(@RequestBody @Valid ExchangeItemsDto exchangeItemsDto, @PathVariable(value = "id") UUID id) {
        var exchangeItemsModelOptional = exchangeItemsService.findById(id);
        if (!exchangeItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Items not found");
        var exchangeItemsModel = exchangeItemsModelOptional.get();
        BeanUtils.copyProperties(exchangeItemsDto, exchangeItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeItemsService.save(exchangeItemsModel));
    }
}
