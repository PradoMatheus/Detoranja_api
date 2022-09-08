package com.detoranja.models;

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
@Table(name = "tb_product")
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategoryModel productCategory;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyModel companyModel;
    @ManyToMany
    @JoinTable(name = "tb_product_attribute_rel",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_attribute_id"))
    private List<ProductAttributeModel> productAttributeModels;
    @Column(nullable = false)
    private double price;
    @Column()
    private String internal_reference;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userModel;
    @Column(nullable = false)
    private LocalDateTime create_date;
}
