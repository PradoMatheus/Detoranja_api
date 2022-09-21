package com.detoranja.models;

import com.detoranja.enums.TypePayment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_client_payment")
public class ClientPaymentModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("clientPaymentModels")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;
    @Column(nullable = false)
    private TypePayment typePayment;
    @Column(nullable = false)
    private String alias;
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
    @Column()
    private boolean favorite;
}
