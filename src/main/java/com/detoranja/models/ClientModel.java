package com.detoranja.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_client")
public class ClientModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private long cpf;
    @Column(nullable = false)
    private LocalDate birth_data;
    @Column(nullable = false)
    private LocalDateTime create_date;
    @JsonIgnoreProperties("clientModel")
    @OneToMany(mappedBy = "clientModel")
    private List<ClientContactModel> clientContactModels;
    @JsonIgnoreProperties("clientModel")
    @OneToMany(mappedBy = "clientModel")
    private List<ClientAddressModel> clientAddressModels;
    @JsonIgnoreProperties("clientModel")
    @OneToMany(mappedBy = "clientModel")
    private List<ClientPaymentModel> clientPaymentModels;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyModel companyModel;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;
    @Column()
    private boolean enabled;
}
