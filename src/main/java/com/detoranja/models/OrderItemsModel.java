package com.detoranja.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_order_items")
public class OrderItemsModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel orderModel;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel productModel;
    @Column()
    private int quantity;
    @Column()
    private double price;
}
