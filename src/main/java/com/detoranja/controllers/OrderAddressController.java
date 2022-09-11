package com.detoranja.controllers;

import com.detoranja.dtos.OrderAddressDto;
import com.detoranja.dtos.OrderDto;
import com.detoranja.dtos.OrderItemsDto;
import com.detoranja.models.OrderAddressModel;
import com.detoranja.models.OrderItemsModel;
import com.detoranja.services.OrderAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/order/address")
public class OrderAddressController {

    final OrderAddressService orderAddressService;

    public OrderAddressController(OrderAddressService orderAddressService) {
        this.orderAddressService = orderAddressService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrderAddress(@RequestBody @Valid OrderAddressDto orderAddressDto) {
        var orderAddressModel = new OrderAddressModel();
        BeanUtils.copyProperties(orderAddressDto, orderAddressModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderAddressService.save(orderAddressModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderAddressModel>> getAllOrderAddress() {
        return ResponseEntity.status(HttpStatus.OK).body(orderAddressService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneOrderAddress(@PathVariable(value = "id") UUID id) {
        var orderAddressModelOptional = orderAddressService.findById(id);
        if (!orderAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Address not found");
        return ResponseEntity.status(HttpStatus.OK).body(orderAddressModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOrderAddress(@PathVariable(value = "id") UUID id) {
        var orderAddressModelOptional = orderAddressService.findById(id);
        if (!orderAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Address not found");
        orderAddressService.delete(orderAddressModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order Address deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrderAddress(@RequestBody @Valid OrderAddressDto orderAddressDto, @PathVariable(value = "id") UUID id) {
        var orderAddressModelOptional = orderAddressService.findById(id);
        if (!orderAddressModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Address not found");
        var orderAddressModel = orderAddressModelOptional.get();
        BeanUtils.copyProperties(orderAddressDto, orderAddressModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderAddressService.save(orderAddressModel));
    }
}
