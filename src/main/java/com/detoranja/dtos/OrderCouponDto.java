package com.detoranja.dtos;

import com.detoranja.models.CouponModel;
import com.detoranja.models.OrderModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class OrderCouponDto implements Serializable {
    @NotBlank
    private OrderModel orderModel;
    @NotBlank
    private CouponModel couponModel;
    @NotBlank
    private double value_discount;
}
