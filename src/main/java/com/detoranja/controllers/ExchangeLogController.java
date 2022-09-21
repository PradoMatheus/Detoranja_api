package com.detoranja.controllers;

import com.detoranja.dtos.ExchangeLogDto;
import com.detoranja.models.ExchangeLogModel;
import com.detoranja.services.ExchangeLogService;
import com.detoranja.services.ExchangeService;
import com.detoranja.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/exchange/log")
public class ExchangeLogController {

    final ExchangeLogService exchangeLogService;
    final ExchangeService exchangeService;
    final UserService userService;

    public ExchangeLogController(ExchangeLogService exchangeLogService, ExchangeService exchangeService, UserService userService) {
        this.exchangeLogService = exchangeLogService;
        this.exchangeService = exchangeService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveExchangeLog(@RequestBody @Valid ExchangeLogDto exchangeLogDto, @AuthenticationPrincipal User user) {
        if (exchangeService.findById(exchangeLogDto.getExchangeModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Exchange not found");
        var exchangeLogModel = new ExchangeLogModel();
        BeanUtils.copyProperties(exchangeLogDto, exchangeLogModel);
        exchangeLogModel.setUserModel(userService.findByUsername(user.getUsername()));
        exchangeLogModel.setDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(exchangeLogService.save(exchangeLogModel));
    }

    @GetMapping
    public ResponseEntity<List<ExchangeLogModel>> getAllExchangeLog() {
        return ResponseEntity.status(HttpStatus.OK).body(exchangeLogService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneExchangeLog(@PathVariable(value = "id") UUID id) {
        var exchangeLogModelOptional = exchangeLogService.findById(id);
        if (!exchangeLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Log not found");
        return ResponseEntity.status(HttpStatus.OK).body(exchangeLogModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteExchangeLog(@PathVariable(value = "id") UUID id) {
        var exchangeLogModelOptional = exchangeLogService.findById(id);
        if (!exchangeLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Log not found");
        exchangeLogService.delete(exchangeLogModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Exchange Log deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateExchangeLog(@RequestBody @Valid ExchangeLogDto exchangeLogDto, @PathVariable(value = "id") UUID id) {
        var exchangeLogModelOptional = exchangeLogService.findById(id);
        if (!exchangeLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exchange Log not found");
        else if (exchangeService.findById(exchangeLogDto.getExchangeModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Exchange not found");
        var exchangeLogModel = exchangeLogModelOptional.get();
        BeanUtils.copyProperties(exchangeLogDto, exchangeLogModel);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeLogService.save(exchangeLogModel));
    }
}
