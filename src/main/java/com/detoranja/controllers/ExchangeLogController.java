package com.detoranja.controllers;

import com.detoranja.dtos.ExchangeItemsDto;
import com.detoranja.dtos.ExchangeLogDto;
import com.detoranja.models.ExchangeItemsModel;
import com.detoranja.models.ExchangeLogModel;
import com.detoranja.services.ExchangeLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/exchange/log")
public class ExchangeLogController {

    final ExchangeLogService exchangeLogService;

    public ExchangeLogController(ExchangeLogService exchangeLogService) {
        this.exchangeLogService = exchangeLogService;
    }

    @PostMapping
    public ResponseEntity<Object> saveExchangeLog(@RequestBody @Valid ExchangeLogDto exchangeLogDto) {
        var exchangeLogModel = new ExchangeLogModel();
        BeanUtils.copyProperties(exchangeLogDto, exchangeLogModel);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeLogService.save(exchangeLogModel));
    }

    @GetMapping
    public ResponseEntity<List<ExchangeLogModel>> getAllExchangeLog() {
        return ResponseEntity.status(HttpStatus.OK).body(exchangeLogService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneExchangeLog(@PathVariable(value = "id") UUID id) {
        var exchangeLogModelOptional= exchangeLogService.findById(id);
        if (!exchangeLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Log not found");
        return ResponseEntity.status(HttpStatus.OK).body(exchangeLogModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteExchangeLog(@PathVariable(value = "id") UUID id) {
        var exchangeLogModelOptional= exchangeLogService.findById(id);
        if (!exchangeLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Log not found");
        exchangeLogService.delete(exchangeLogModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Exchange Log deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateExchangeLog(@RequestBody @Valid ExchangeLogDto exchangeLogDto, @PathVariable(value = "id") UUID id) {
        var exchangeLogModelOptional= exchangeLogService.findById(id);
        if (!exchangeLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Log not found");
        var exchangeLogModel = exchangeLogModelOptional.get();
        BeanUtils.copyProperties(exchangeLogDto, exchangeLogModel);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeLogService.save(exchangeLogModel));
    }
}
