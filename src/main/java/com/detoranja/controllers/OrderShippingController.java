package com.detoranja.controllers;

import com.detoranja.dtos.OrderCouponDto;
import com.detoranja.dtos.OrderShippingDto;
import com.detoranja.models.OrderCouponModel;
import com.detoranja.models.OrderShippingModel;
import com.detoranja.services.OrderShippingService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/order/shipping")
public class OrderShippingController {

    final OrderShippingService orderShippingService;

    public OrderShippingController(OrderShippingService orderShippingService) {
        this.orderShippingService = orderShippingService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrderShipping(@RequestBody @Valid OrderShippingDto orderShippingDto) {
        var orderShippingModel = new OrderShippingModel();
        BeanUtils.copyProperties(orderShippingDto, orderShippingModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderShippingService.save(orderShippingModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderShippingModel>> getAllOrderShipping() {
        return ResponseEntity.status(HttpStatus.OK).body(orderShippingService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneOrderShipping(@PathVariable(value = "id") UUID id) {
        var orderShippingModelOptional = orderShippingService.findById(id);
        if (!orderShippingModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Shipping not found");
        return ResponseEntity.status(HttpStatus.OK).body(orderShippingModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOrderShipping(@PathVariable(value = "id") UUID id) {
        var orderShippingModelOptional = orderShippingService.findById(id);
        if (!orderShippingModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Shipping not found");
        orderShippingService.delete(orderShippingModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order Shipping deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrderShipping(@RequestBody @Valid OrderShippingDto orderShippingDto, @PathVariable(value = "id") UUID id) {
        var orderShippingModelOptional = orderShippingService.findById(id);
        if (!orderShippingModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Shipping not found");
        var orderShippingModel = orderShippingModelOptional.get();
        BeanUtils.copyProperties(orderShippingDto, orderShippingModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderShippingService.save(orderShippingModel));
    }
}
