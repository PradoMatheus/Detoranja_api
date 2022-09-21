package com.detoranja.models;

import com.detoranja.enums.StatusExchange;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_exchange")
public class ExchangeModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderModel orderModel;
    @JsonIgnoreProperties("exchangeModel")
    @OneToMany(mappedBy = "exchangeModel")
    private List<ExchangeItemsModel> exchangeItemsModels;
    @JsonIgnoreProperties("exchangeModel")
    @OneToMany(mappedBy = "exchangeModel")
    private List<ExchangeLogModel> exchangeLogModels;
    @JsonIgnoreProperties("exchangeModels")
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyModel companyModel;
    @Column(nullable = false)
    private StatusExchange status;
    @Column(nullable = false)
    private LocalDateTime date_create;
}
