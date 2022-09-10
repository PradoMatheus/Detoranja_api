package com.detoranja.controllers;

import com.detoranja.dtos.CartDto;
import com.detoranja.dtos.SupplierContactDto;
import com.detoranja.models.CartModel;
import com.detoranja.models.SupplierContactModel;
import com.detoranja.services.CartService;
import com.detoranja.services.SupplierContactService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/cart")
public class CartController {

    final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCart(@RequestBody @Valid CartDto cartDto) {
        var cartModel = new CartModel();
        BeanUtils.copyProperties(cartDto, cartModel);
        return ResponseEntity.status(HttpStatus.OK).body(cartService.save(cartModel));
    }

    @GetMapping
    public ResponseEntity<List<CartModel>> getAllCart() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneCart(@PathVariable(value = "id") UUID id) {
        var cartModelOptional = cartService.findById(id);
        if (!cartModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        return ResponseEntity.status(HttpStatus.OK).body(cartModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCart(@PathVariable(value = "id") UUID id) {
        var cartModelOptional = cartService.findById(id);
        if (!cartModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        cartService.delete(cartModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cart deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateCart(@RequestBody @Valid CartDto cartDto, @PathVariable(value = "id") UUID id) {
        var cartModelOptional = cartService.findById(id);
        if (!cartModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        var cartModel = cartModelOptional.get();
        BeanUtils.copyProperties(cartDto, cartModel);
        return ResponseEntity.status(HttpStatus.OK).body(cartService.save(cartModel));
    }
}
