package com.detoranja.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_purchase_items")
public class PurchaseItemsModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double price;
    @Column()
    private String observation;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product_model;
}
