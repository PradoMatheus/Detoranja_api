package com.detoranja.dtos;

import com.detoranja.models.AttributeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AttributeValueDto {
    @NotBlank
    private String name;
    @NotBlank
    private AttributeModel attributeModel;
}
