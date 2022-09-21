package com.detoranja.controllers;

import com.detoranja.dtos.OrderLogDto;
import com.detoranja.models.OrderLogModel;
import com.detoranja.services.OrderLogService;
import com.detoranja.services.OrderService;
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
@RequestMapping(value = "/order/log")
public class OrderLogController {

    final OrderLogService orderLogService;
    final UserService userService;
    final OrderService orderService;

    public OrderLogController(OrderLogService orderLogService, UserService userService, OrderService orderService) {
        this.orderLogService = orderLogService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrderLog(@RequestBody @Valid OrderLogDto orderLogDto, @AuthenticationPrincipal User user) {
        if (orderService.findById(orderLogDto.getOrderModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        var orderLogModel = new OrderLogModel();
        BeanUtils.copyProperties(orderLogDto, orderLogModel);
        orderLogModel.setUserModel(userService.findByUsername(user.getUsername()));
        orderLogModel.setDate(LocalDateTime.now(ZoneId.of("UTC")));
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
        else if (orderService.findById(orderLogDto.getOrderModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        var orderLogModel = orderLogModelOptional.get();
        BeanUtils.copyProperties(orderLogDto, orderLogModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderLogService.save(orderLogModel));
    }
}
