package com.detoranja.models;

import com.detoranja.enums.StatusExchange;
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
@Table(name = "tb_exchange_log")
public class ExchangeLogModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("exchangeLogModels")
    @ManyToOne
    @JoinColumn(name = "exchange_id", nullable = false)
    private ExchangeModel exchangeModel;
    @Column(nullable = false)
    private StatusExchange status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
    @Column(nullable = false)
    private LocalDateTime date;
}