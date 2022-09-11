package com.detoranja.services;

import com.detoranja.models.OrderItemsModel;
import com.detoranja.repositories.OrderItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemsService {

    final OrderItemsRepository orderItemsRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public OrderItemsModel save(OrderItemsModel orderItemsModel) {
        return orderItemsRepository.save(orderItemsModel);
    }

    public List<OrderItemsModel> findAll() {
        return orderItemsRepository.findAll();
    }

    public Optional<OrderItemsModel> findById(UUID id) {
        return orderItemsRepository.findById(id);
    }

    public void delete(OrderItemsModel orderItemsModel) {
        orderItemsRepository.delete(orderItemsModel);
    }
}
