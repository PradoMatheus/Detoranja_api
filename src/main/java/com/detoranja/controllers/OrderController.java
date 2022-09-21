package com.detoranja.controllers;

import com.detoranja.dtos.OrderDto;
import com.detoranja.models.OrderModel;
import com.detoranja.services.ClientService;
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
@RequestMapping(value = "/order")
public class OrderController {

    final OrderService orderService;
    final ClientService clientService;
    final UserService userService;

    public OrderController(OrderService orderService, ClientService clientService, UserService userService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody @Valid OrderDto orderDto, @AuthenticationPrincipal User user) {
        if (clientService.findById(orderDto.getClientModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Client not found");
        var orderModel = new OrderModel();
        BeanUtils.copyProperties(orderDto, orderModel);
        orderModel.setOrder_date(LocalDateTime.now(ZoneId.of("UTC")));
        orderModel.setUser_model(userService.findByUsername(user.getUsername()));
        return ResponseEntity.status(HttpStatus.OK).body(orderService.save(orderModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> getAllOrder() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") UUID id) {
        var orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        return ResponseEntity.status(HttpStatus.OK).body(orderModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") UUID id) {
        var orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        orderService.delete(orderModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrder(@RequestBody @Valid OrderDto orderDto, @PathVariable(value = "id") UUID id) {
        var orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        if (clientService.findById(orderModelOptional.get().getClientModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Client not found");
        var orderModel = orderModelOptional.get();
        BeanUtils.copyProperties(orderDto, orderModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.save(orderModel));
    }
}
