package com.detoranja.models;

import com.detoranja.enums.TypePayment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_order_payment")
public class OrderPaymentModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel orderModel;
    @Column(nullable = false)
    private TypePayment typePayment;
    @Column(nullable = false)
    private LocalDate date_validate;
    @Column(nullable = false)
    private String cvv;
    @Column(nullable = false)
    private long number_card;
    @Column(nullable = false)
    private String flag;
    @Column(nullable = false)
    private String name;
}
