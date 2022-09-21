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
@Table(name = "tb_exchange_items")
public class ExchangeItemsModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("exchangeItemsModels")
    @ManyToOne
    @JoinColumn(name = "exchange_id")
    private ExchangeModel exchangeModel;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel productModel;
    @Column()
    private int quantity;
    @Column()
    private double price;
}
