package com.detoranja.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_product_attribute")
public class ProductAttributeModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "attribute_model_id", nullable = false)
    private AttributeModel attributeModel;
    @ManyToMany(mappedBy = "productAttributeModels")
    private List<ProductModel> productModel;
    @JsonIgnoreProperties("productAttributeModel")
    @OneToMany(mappedBy = "productAttributeModel")
    private List<ProductAttributeValueModel> productAttributeValueModels;
}
