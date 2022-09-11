package com.detoranja.controllers;

import com.detoranja.dtos.OrderDto;
import com.detoranja.dtos.OrderItemsDto;
import com.detoranja.models.OrderItemsModel;
import com.detoranja.services.OrderItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/order/items")
public class OrderItemsController {

    final OrderItemsService orderItemsService;

    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrderItems(@RequestBody @Valid OrderItemsDto orderItemsDto) {
        var orderItemsModel = new OrderItemsModel();
        BeanUtils.copyProperties(orderItemsDto, orderItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemsService.save(orderItemsModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderItemsModel>> getAllOrderItems() {
        return ResponseEntity.status(HttpStatus.OK).body(orderItemsService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneOrderItems(@PathVariable(value = "id") UUID id) {
        var orderItemsModelOptional = orderItemsService.findById(id);
        if (!orderItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Items not found");
        return ResponseEntity.status(HttpStatus.OK).body(orderItemsModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOrderItems(@PathVariable(value = "id") UUID id) {
        var orderItemsModelOptional = orderItemsService.findById(id);
        if (!orderItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Items not found");
        orderItemsService.delete(orderItemsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order Items deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrderItems(@RequestBody @Valid OrderDto orderDto, @PathVariable(value = "id") UUID id) {
        var orderItemsModelOptional = orderItemsService.findById(id);
        if (!orderItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Items not found");
        var orderItemsModel = orderItemsModelOptional.get();
        BeanUtils.copyProperties(orderDto, orderItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemsService.save(orderItemsModel));
    }
}
