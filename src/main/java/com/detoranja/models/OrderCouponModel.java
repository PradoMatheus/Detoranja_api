package com.detoranja.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_order_coupon")
public class OrderCouponModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("orderCouponModels")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel orderModel;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private CouponModel couponModel;
    @Column(nullable = false)
    private double value_discount;
}
