package com.detoranja.services;

import com.detoranja.models.CartModel;
import com.detoranja.models.SupplierAddressModel;
import com.detoranja.repositories.CartRepository;
import com.detoranja.repositories.SupplierAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartModel save(CartModel cartModel) {
        return cartRepository.save(cartModel);
    }

    public List<CartModel> findAll() {
        return cartRepository.findAll();
    }

    public Optional<CartModel> findById(UUID id) {
        return cartRepository.findById(id);
    }

    public void delete(CartModel cartModel) {
        cartRepository.delete(cartModel);
    }
}
