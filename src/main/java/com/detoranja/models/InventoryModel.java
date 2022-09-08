package com.detoranja.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_inventory")
public class InventoryModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "product_model_id", unique = true, nullable = false)
    private ProductModel productModel;
    @Column(nullable = false)
    private int balance;
    @Column(nullable = false)
    private int reserve;
}
