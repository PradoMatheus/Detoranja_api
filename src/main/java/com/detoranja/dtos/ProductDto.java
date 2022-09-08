package com.detoranja.dtos;

import com.detoranja.models.ProductCategoryModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private ProductCategoryModel productCategory;
    @NotBlank
    private double price;
    private String internal_reference;
}
