package com.detoranja.models;

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
@Table(name = "tb_supplier")
public class SupplierModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 500)
    private String social_reason;
    @Column(length = 500)
    private String fantasy_name;
    @Column(unique = true, length = 13)
    private String cnpj;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyModel companyModel;
    @Column(unique = true)
    private long state_registration;
    @JsonIgnoreProperties("supplierModel")
    @OneToMany(mappedBy = "supplierModel")
    private List<SupplierAddressModel> supplierAddressModels;
    @JsonIgnoreProperties("supplierModel")
    @OneToMany(mappedBy = "supplierModel")
    private List<SupplierContactModel> supplierContactModels;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user_model;
    @Column(nullable = false)
    private LocalDateTime create_date;
}
