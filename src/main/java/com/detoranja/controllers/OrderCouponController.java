package com.detoranja.controllers;

import com.detoranja.dtos.OrderCouponDto;
import com.detoranja.models.OrderCouponModel;
import com.detoranja.services.CouponService;
import com.detoranja.services.OrderCouponService;
import com.detoranja.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/order/coupon")
public class OrderCouponController {

    final OrderCouponService orderCouponService;
    final OrderService orderService;
    final CouponService couponService;

    public OrderCouponController(OrderCouponService orderCouponService, OrderService orderService, CouponService couponService) {
        this.orderCouponService = orderCouponService;
        this.orderService = orderService;
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrderCoupon(@RequestBody @Valid OrderCouponDto orderCouponDto) {
        if (orderService.findById(orderCouponDto.getOrderModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Order not found");
        else if (couponService.findById(orderCouponDto.getCouponModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Coupon not found");
        var orderCouponModel = new OrderCouponModel();
        BeanUtils.copyProperties(orderCouponDto, orderCouponModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderCouponService.save(orderCouponModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderCouponModel>> getAllOrderCoupon() {
        return ResponseEntity.status(HttpStatus.OK).body(orderCouponService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneOrderCoupon(@PathVariable(value = "id") UUID id) {
        var orderCouponModelOptional = orderCouponService.findById(id);
        if (!orderCouponModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Coupon not found");
        return ResponseEntity.status(HttpStatus.OK).body(orderCouponModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOrderCoupon(@PathVariable(value = "id") UUID id) {
        var orderCouponModelOptional = orderCouponService.findById(id);
        if (!orderCouponModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Coupon not found");
        orderCouponService.delete(orderCouponModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order Coupon deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOrderCoupon(@RequestBody @Valid OrderCouponDto orderCouponDto, @PathVariable(value = "id") UUID id) {
        var orderCouponModelOptional = orderCouponService.findById(id);
        if (!orderCouponModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Coupon not found");
        var orderCouponModel = orderCouponModelOptional.get();
        BeanUtils.copyProperties(orderCouponDto, orderCouponModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderCouponService.save(orderCouponModel));
    }
}
