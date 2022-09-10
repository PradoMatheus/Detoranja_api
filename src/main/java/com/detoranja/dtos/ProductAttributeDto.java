package com.detoranja.dtos;

import com.detoranja.models.AttributeModel;
import com.detoranja.models.AttributeValueModel;
import com.detoranja.models.ProductModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ProductAttributeDto implements Serializable {
    @NotBlank
    private AttributeModel attributeModel;
    @NotBlank
    private ProductModel productModel;
}
