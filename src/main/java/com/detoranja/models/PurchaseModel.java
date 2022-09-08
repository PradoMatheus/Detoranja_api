package com.detoranja.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_purchase")
public class PurchaseModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private int quantity;
    @Column(unique = true)
    private String fiscal_note;
    @Column(nullable = false)
    private LocalDateTime create_date;
    @Column(nullable = false)
    private double price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user_model;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierModel supplierModel;
}
