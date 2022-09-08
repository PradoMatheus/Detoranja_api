package com.detoranja.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_cart")
public class CartModel implements Serializable {
    private static final long serialVersionUID = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "client_id", unique = true)
    private ClientModel clientModel;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double total_value;
    @OneToMany(mappedBy = "cartModel")
    private List<CartItemsModel> cartItemsModels;
}
