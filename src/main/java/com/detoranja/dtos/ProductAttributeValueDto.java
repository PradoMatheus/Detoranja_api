package com.detoranja.dtos;

import com.detoranja.models.AttributeValueModel;
import com.detoranja.models.ProductAttributeModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ProductAttributeValueDto implements Serializable {
    @NotBlank
    private ProductAttributeModel productAttributeModel;
    @NotBlank
    private AttributeValueModel attributeValueModel;
}
