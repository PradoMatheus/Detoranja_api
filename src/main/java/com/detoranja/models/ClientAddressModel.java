package com.detoranja.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_client_address")
public class ClientAddressModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("clientAddressModels")
    @ManyToOne(targetEntity = ClientModel.class)
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;
    @Column(nullable = false)
    private long zip;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String state;
    @Column()
    private String reference;
    @Column()
    private boolean favorite;
}
