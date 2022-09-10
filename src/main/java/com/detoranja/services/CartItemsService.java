package com.detoranja.services;

import com.detoranja.models.CartItemsModel;
import com.detoranja.models.CartModel;
import com.detoranja.repositories.CartItemsRepository;
import com.detoranja.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemsService {

    final CartItemsRepository cartItemsRepository;

    public CartItemsService(CartItemsRepository cartItemsRepository) {
        this.cartItemsRepository = cartItemsRepository;
    }

    public CartItemsModel save(CartItemsModel cartItemsModel) {
        return cartItemsRepository.save(cartItemsModel);
    }

    public List<CartItemsModel> findAll() {
        return cartItemsRepository.findAll();
    }

    public Optional<CartItemsModel> findById(UUID id) {
        return cartItemsRepository.findById(id);
    }

    public void delete(CartItemsModel cartItemsModel) {
        cartItemsRepository.delete(cartItemsModel);
    }
}
