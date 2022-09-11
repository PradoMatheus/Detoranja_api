package com.detoranja.services;

import com.detoranja.models.OrderItemsModel;
import com.detoranja.models.OrderLogModel;
import com.detoranja.repositories.OrderLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderLogService {

    final OrderLogRepository orderLogRepository;

    public OrderLogService(OrderLogRepository orderLogRepository) {
        this.orderLogRepository = orderLogRepository;
    }

    public OrderLogModel save(OrderLogModel orderLogModel) {
        return orderLogRepository.save(orderLogModel);
    }

    public List<OrderLogModel> findAll() {
        return orderLogRepository.findAll();
    }

    public Optional<OrderLogModel> findById(UUID id) {
        return orderLogRepository.findById(id);
    }

    public void delete(OrderLogModel orderLogModel) {
        orderLogRepository.delete(orderLogModel);
    }
}
