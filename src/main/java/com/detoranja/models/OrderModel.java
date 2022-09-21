package com.detoranja.models;

import com.detoranja.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_order")
public class OrderModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("orderModel")
    @OneToMany(mappedBy = "orderModel")
    private List<OrderItemsModel> orderItemsModels;
    @JsonIgnoreProperties("orderModel")
    @OneToMany(mappedBy = "orderModel")
    private List<OrderAddressModel> orderAddressModels;
    @JsonIgnoreProperties("orderModel")
    @OneToMany(mappedBy = "orderModel")
    private List<OrderPaymentModel> orderPaymentModels;
    @JsonIgnoreProperties("orderModel")
    @OneToMany(mappedBy = "orderModel")
    private List<OrderCouponModel> orderCouponModels;
    @JsonIgnoreProperties("orderModel")
    @OneToMany(mappedBy = "orderModel")
    private List<ExchangeModel> exchangeModels;
    @JsonIgnoreProperties("orderModel")
    @OneToMany(mappedBy = "orderModel")
    private List<OrderShippingModel> orderShippingModels;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double total_value;
    @Column(nullable = false)
    private StatusOrder status;
    @JsonIgnoreProperties("orderModel")
    @OneToMany(mappedBy = "orderModel")
    private List<OrderLogModel> orderLogModels;
    @Column(nullable = false)
    private LocalDateTime order_date;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user_model;
}
