package com.detoranja.models;

import com.detoranja.enums.StatusExchange;
import com.detoranja.enums.StatusOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_exchange_log")
public class ExchangeLogModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "exchange_id")
    private ExchangeModel exchangeModel;
    @Column(nullable = false)
    private StatusExchange olderStatus;
    @Column(nullable = false)
    private StatusExchange newStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
    @Column(nullable = false)
    private LocalDateTime date;
}