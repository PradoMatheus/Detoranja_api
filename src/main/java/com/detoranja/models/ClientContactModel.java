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
@Table(name = "tb_client_contact")
public class ClientContactModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("clientContactModels")
    @ManyToOne(targetEntity = ClientModel.class)
    @JoinColumn(name = "client_id")
    private ClientModel clientModel;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long cellphone;
    @Column(nullable = false)
    private long phone;
}
