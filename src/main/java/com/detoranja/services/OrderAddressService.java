package com.detoranja.services;

import com.detoranja.models.OrderAddressModel;
import com.detoranja.repositories.OrderAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderAddressService {

    final OrderAddressRepository orderAddressRepository;

    public OrderAddressService(OrderAddressRepository orderAddressRepository) {
        this.orderAddressRepository = orderAddressRepository;
    }

    public OrderAddressModel save(OrderAddressModel orderAddressModel) {
        return orderAddressRepository.save(orderAddressModel);
    }

    public List<OrderAddressModel> findAll() {
        return orderAddressRepository.findAll();
    }

    public Optional<OrderAddressModel> findById(UUID id) {
        return orderAddressRepository.findById(id);
    }

    public void delete(OrderAddressModel orderAddressModel) {
        orderAddressRepository.delete(orderAddressModel);
    }
}
