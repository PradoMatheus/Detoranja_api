package com.detoranja.services;

import com.detoranja.models.OrderModel;
import com.detoranja.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModel save(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }

    public Optional<OrderModel> findById(UUID id) {
        return orderRepository.findById(id);
    }

    public void delete(OrderModel orderModel) {
        orderRepository.delete(orderModel);
    }
}
