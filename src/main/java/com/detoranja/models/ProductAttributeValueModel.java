package com.detoranja.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_product_attribute_value_rel")
public class ProductAttributeValueModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "product_attribute_model_id", nullable = false)
    private ProductAttributeModel productAttributeModel;
    @ManyToOne
    @JoinColumn(name = "attribute_value_model_id", nullable = false)
    private AttributeValueModel attributeValueModel;
}
