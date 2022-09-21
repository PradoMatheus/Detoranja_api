package com.detoranja.controllers;

import com.detoranja.dtos.ExchangeDto;
import com.detoranja.models.ExchangeModel;
import com.detoranja.services.CompanyService;
import com.detoranja.services.ExchangeService;
import com.detoranja.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/exchange")
public class ExchangeController {

    final ExchangeService exchangeService;
    final CompanyService companyService;
    final OrderService orderService;

    public ExchangeController(ExchangeService exchangeService, CompanyService companyService, OrderService orderService) {
        this.exchangeService = exchangeService;
        this.companyService = companyService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> saveExchange(@RequestBody @Valid ExchangeDto exchangeDto) {
        if (companyService.findById(exchangeDto.getCompanyModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Company not found");
        else if (orderService.findById(exchangeDto.getOrderModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Order not found");
        var exchangeModel = new ExchangeModel();
        BeanUtils.copyProperties(exchangeDto, exchangeModel);
        exchangeModel.setDate_create(LocalDateTime.now(ZoneId.of("UTC")));
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
        else if (companyService.findById(exchangeDto.getCompanyModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Company not found");
        else if (orderService.findById(exchangeDto.getOrderModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Order not found");
        var exchangeModel = exchangeModelOptional.get();
        BeanUtils.copyProperties(exchangeDto, exchangeModel);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.save(exchangeModel));
    }
}
