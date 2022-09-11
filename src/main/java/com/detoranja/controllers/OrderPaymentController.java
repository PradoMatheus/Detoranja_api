package com.detoranja.controllers;

import com.detoranja.dtos.OrderCouponDto;
import com.detoranja.dtos.OrderPaymentDto;
import com.detoranja.models.OrderCouponModel;
import com.detoranja.models.OrderPaymentModel;
import com.detoranja.services.OrderPaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/order/payment")
public class OrderPaymentController {

    final OrderPaymentService orderPaymentService;

    public OrderPaymentController(OrderPaymentService orderPaymentService) {
        this.orderPaymentService = orderPaymentService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrderPayment(@RequestBody @Valid OrderPaymentDto orderPaymentDto) {
        var orderPaymentModel = new OrderPaymentModel();
        BeanUtils.copyProperties(orderPaymentDto, orderPaymentModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderPaymentService.save(orderPaymentModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderPaymentModel>> getAllOrderPayment() {
        return ResponseEntity.status(HttpStatus.OK).body(orderPaymentService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneOrderPayment(@PathVariable(value = "id") UUID id) {
        var orderPaymentModelOptional = orderPaymentService.findById(id);
        if (!orderPaymentModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Payment not found");
        return ResponseEntity.status(HttpStatus.OK).body(orderPaymentModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOrderPayment(@PathVariable(value = "id") UUID id) {
        var orderPaymentModelOptional = orderPaymentService.findById(id);
        if (!orderPaymentModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Payment not found");
        orderPaymentService.delete(orderPaymentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order Payment deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrderPayment(@RequestBody @Valid OrderPaymentDto orderPaymentDto, @PathVariable(value = "id") UUID id) {
        var orderPaymentModelOptional = orderPaymentService.findById(id);
        if (!orderPaymentModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Payment not found");
        var orderPaymentModel = orderPaymentModelOptional.get();
        BeanUtils.copyProperties(orderPaymentDto, orderPaymentModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderPaymentService.save(orderPaymentModel));
    }
}
