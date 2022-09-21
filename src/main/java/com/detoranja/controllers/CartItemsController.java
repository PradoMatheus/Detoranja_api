package com.detoranja.controllers;

import com.detoranja.dtos.CartItemsDto;
import com.detoranja.models.CartItemsModel;
import com.detoranja.services.CartItemsService;
import com.detoranja.services.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/cart/items")
public class CartItemsController {

    final CartItemsService cartItemsService;
    final CartService cartService;

    public CartItemsController(CartItemsService cartItemsService, CartService cartService) {
        this.cartItemsService = cartItemsService;
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCartItems(@RequestBody @Valid CartItemsDto cartItemsDto) {
        if (cartService.findById(cartItemsDto.getCartModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        var cartItemsModel = new CartItemsModel();
        BeanUtils.copyProperties(cartItemsDto, cartItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemsService.save(cartItemsModel));
    }

    @GetMapping
    public ResponseEntity<List<CartItemsModel>> getAllCartItems() {
        return ResponseEntity.status(HttpStatus.OK).body(cartItemsService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneCartItems(@PathVariable(value = "id") UUID id) {
        var cartItemsModelOptional = cartItemsService.findById(id);
        if (!cartItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart Items not found");
        return ResponseEntity.status(HttpStatus.OK).body(cartItemsModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCartItems(@PathVariable(value = "id") UUID id) {
        var cartItemsModelOptional = cartItemsService.findById(id);
        if (!cartItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart Items not found");
        cartItemsService.delete(cartItemsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cart Items deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateCartItems(@RequestBody @Valid CartItemsDto cartItemsDto, @PathVariable(value = "id") UUID id) {
        var cartItemsModelOptional = cartItemsService.findById(id);
        if (!cartItemsModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart Items not found");
        if (cartService.findById(cartItemsDto.getCartModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        var cartItemsModel = cartItemsModelOptional.get();
        BeanUtils.copyProperties(cartItemsDto, cartItemsModel);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemsService.save(cartItemsModel));
    }
}
