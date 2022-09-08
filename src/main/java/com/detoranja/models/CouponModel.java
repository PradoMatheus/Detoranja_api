package com.detoranja.models;

import com.detoranja.enums.TypeCoupon;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_coupon")
public class CouponModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column()
    private String description;
    @Column(nullable = false)
    private TypeCoupon typeCoupon;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyModel companyModel;
    @Column()
    private double percentage;
    @Column()
    private double value;
    @Column()
    private LocalDate date_validate;
    @Column()
    private LocalDateTime create_date;
    @Column()
    private boolean enable;
}
