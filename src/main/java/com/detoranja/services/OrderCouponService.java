package com.detoranja.services;

import com.detoranja.models.OrderCouponModel;
import com.detoranja.repositories.OrderCouponRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderCouponService {

    final OrderCouponRepository orderCouponRepository;

    public OrderCouponService(OrderCouponRepository orderCouponRepository) {
        this.orderCouponRepository = orderCouponRepository;
    }

    public OrderCouponModel save(OrderCouponModel orderCouponModel) {
        return orderCouponRepository.save(orderCouponModel);
    }

    public List<OrderCouponModel> findAll() {
        return orderCouponRepository.findAll();
    }

    public Optional<OrderCouponModel> findById(UUID id) {
        return orderCouponRepository.findById(id);
    }

    public void delete(OrderCouponModel orderCouponModel) {
        orderCouponRepository.delete(orderCouponModel);
    }
}
