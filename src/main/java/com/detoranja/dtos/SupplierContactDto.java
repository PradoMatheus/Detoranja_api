package com.detoranja.dtos;

import com.detoranja.models.SupplierModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SupplierContactDto {
    @NotBlank
    private SupplierModel supplierModel;
    @NotBlank
    private String name;
    private long cellphone;
    private long phone;
    private String email;
}
