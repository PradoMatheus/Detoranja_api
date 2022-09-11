package com.detoranja.services;

import com.detoranja.models.OrderPaymentModel;
import com.detoranja.repositories.OrderPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderPaymentService {

    final OrderPaymentRepository orderPaymentRepository;

    public OrderPaymentService(OrderPaymentRepository orderPaymentRepository) {
        this.orderPaymentRepository = orderPaymentRepository;
    }

    public OrderPaymentModel save(OrderPaymentModel orderPaymentModel) {
        return orderPaymentRepository.save(orderPaymentModel);
    }

    public List<OrderPaymentModel> findAll() {
        return orderPaymentRepository.findAll();
    }

    public Optional<OrderPaymentModel> findById(UUID id) {
        return orderPaymentRepository.findById(id);
    }

    public void delete(OrderPaymentModel orderPaymentModel) {
        orderPaymentRepository.delete(orderPaymentModel);
    }
}
