package com.detoranja.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_product_attribute_rel")
public class ProductAttributeModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "attribute_model_id", nullable = false)
    private AttributeModel attributeModel;
    @ManyToOne
    @JoinColumn(name = "attribute_value_id", nullable = false)
    private AttributeValueModel attributeValueModel;
}
