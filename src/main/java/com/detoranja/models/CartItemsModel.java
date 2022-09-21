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
@Table(name = "tb_cart_items")
public class CartItemsModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("cartItemsModels")
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartModel cartModel;
    @Column(nullable = false)
    private int quantity;
}
