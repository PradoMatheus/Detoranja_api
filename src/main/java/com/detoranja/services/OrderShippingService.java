package com.detoranja.services;

import com.detoranja.models.OrderAddressModel;
import com.detoranja.models.OrderShippingModel;
import com.detoranja.repositories.OrderShippingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderShippingService {

    final OrderShippingRepository orderShippingRepository;

    public OrderShippingService(OrderShippingRepository orderShippingRepository) {
        this.orderShippingRepository = orderShippingRepository;
    }

    public OrderShippingModel save(OrderShippingModel orderShippingModel) {
        return orderShippingRepository.save(orderShippingModel);
    }

    public List<OrderShippingModel> findAll() {
        return orderShippingRepository.findAll();
    }

    public Optional<OrderShippingModel> findById(UUID id) {
        return orderShippingRepository.findById(id);
    }

    public void delete(OrderShippingModel orderShippingModel) {
        orderShippingRepository.delete(orderShippingModel);
    }
}
