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
@Table(name = "tb_order_shipping")
public class OrderShippingModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("orderShippingModels")
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderModel orderModel;
    @Column(nullable = false)
    private double value;
    @Column()
    private String tracking_code;
}
