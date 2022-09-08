package com.detoranja.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductCategoryDto {
    @NotBlank
    private String name;
}
