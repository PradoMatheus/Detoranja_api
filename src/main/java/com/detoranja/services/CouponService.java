package com.detoranja.services;

import com.detoranja.models.CouponModel;
import com.detoranja.repositories.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponService {

    final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public boolean existByName(String name) {
        return couponRepository.existsByName(name);
    }

    public CouponModel save(CouponModel couponModel) {
        return couponRepository.save(couponModel);
    }

    public List<CouponModel> findAll() {
        return couponRepository.findAll();
    }

    public Optional<CouponModel> findById(UUID id) {
        return couponRepository.findById(id);
    }

    public void delete(CouponModel couponModel) {
        couponRepository.delete(couponModel);
    }
}
