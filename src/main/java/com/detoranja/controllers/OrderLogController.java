package com.detoranja.controllers;

import com.detoranja.dtos.OrderDto;
import com.detoranja.dtos.OrderItemsDto;
import com.detoranja.dtos.OrderLogDto;
import com.detoranja.models.OrderItemsModel;
import com.detoranja.models.OrderLogModel;
import com.detoranja.services.OrderLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/order/log")
public class OrderLogController {

    final OrderLogService orderLogService;

    public OrderLogController(OrderLogService orderLogService) {
        this.orderLogService = orderLogService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrderLog(@RequestBody @Valid OrderLogDto orderLogDto) {
        var orderLogModel = new OrderLogModel();
        BeanUtils.copyProperties(orderLogDto, orderLogModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderLogService.save(orderLogModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderLogModel>> getAllOrderLog() {
        return ResponseEntity.status(HttpStatus.OK).body(orderLogService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneOrderLog(@PathVariable(value = "id") UUID id) {
        var orderLogModelOptional = orderLogService.findById(id);
        if (!orderLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Log not found");
        return ResponseEntity.status(HttpStatus.OK).body(orderLogModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOrderLog(@PathVariable(value = "id") UUID id) {
        var orderLogModelOptional = orderLogService.findById(id);
        if (!orderLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Log not found");
        orderLogService.delete(orderLogModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order Log deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrderLog(@RequestBody @Valid OrderLogDto orderLogDto, @PathVariable(value = "id") UUID id) {
        var orderLogModelOptional = orderLogService.findById(id);
        if (!orderLogModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Log not found");
        var orderLogModel = orderLogModelOptional.get();
        BeanUtils.copyProperties(orderLogDto, orderLogModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderLogService.save(orderLogModel));
    }
}
