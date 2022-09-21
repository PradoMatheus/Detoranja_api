package com.detoranja.controllers;

import com.detoranja.dtos.OrderItemsDto;
import com.detoranja.models.OrderItemsModel;
import com.detoranja.services.OrderItemsService;
import com.detoranja.services.OrderService;
import com.detoranja.services.ProductService;
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
    final OrderService orderService;
    final ProductService productService;

    public OrderItemsController(OrderItemsService orderItemsService, OrderService orderService, ProductService productService) {
        this.orderItemsService = orderItemsService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrderItems(@RequestBody @Valid OrderItemsDto orderItemsDto) {
        if (orderService.findById(orderItemsDto.getOrderModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Order not found");
        else if (productService.findById(orderItemsDto.getProductModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Product not found");
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
    public ResponseEntity<Object> updateOrderItems(@RequestBody @Valid OrderItemsDto orderItemsDto, @PathVariable(value = "id") UUID id) {
        var orderItemsModelOptional = orderItemsService.findById(id);
        if (!orderItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Items not found");
        else if (orderService.findById(orderItemsDto.getOrderModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Order not found");
        else if (productService.findById(orderItemsDto.getProductModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Product not found");
        var orderItemsModel = orderItemsModelOptional.get();
        BeanUtils.copyProperties(orderItemsDto, orderItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemsService.save(orderItemsModel));
    }
}
