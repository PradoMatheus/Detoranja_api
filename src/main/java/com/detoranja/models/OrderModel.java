package com.detoranja.models;

import com.detoranja.enums.StatusOrder;
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
    @OneToMany(mappedBy = "orderModel")
    private List<OrderItemsModel> orderItemsModels;
    @OneToMany(mappedBy = "orderModel")
    private List<OrderAddressModel> orderAddressModels;
    @OneToMany(mappedBy = "orderModel")
    private List<OrderPaymentModel> orderPaymentModels;
    @OneToMany(mappedBy = "orderModel")
    private List<OrderCouponModel> orderCouponModels;
    @OneToMany(mappedBy = "orderModel")
    private List<ExchangeModel> exchangeModels;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double total_value;
    @Column(nullable = false)
    private StatusOrder status;
    @OneToMany(mappedBy = "orderModel")
    private List<OrderLogModel> orderLogModels;
    @Column(nullable = false)
    private LocalDateTime order_date;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;
    @ManyToOne
    @JoinColumn(name = "client_address_id")
    private ClientAddressModel clientAddressModel;
    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private OrderShippingModel orderShippingModel;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user_model;
}
