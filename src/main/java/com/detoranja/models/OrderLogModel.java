package com.detoranja.models;

import com.detoranja.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_order_log")
public class OrderLogModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("orderLogModels")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel orderModel;
    @Column(nullable = false)
    private StatusOrder status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
    @Column(nullable = false)
    private LocalDateTime date;
}