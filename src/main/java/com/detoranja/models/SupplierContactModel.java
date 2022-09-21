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
@Table(name = "tb_supplier_contact")
public class SupplierContactModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("supplierContactModels")
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierModel supplierModel;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long cellphone;
    @Column(nullable = false)
    private long phone;
    @Column()
    private String email;
}
