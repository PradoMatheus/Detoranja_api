package com.detoranja.dtos;

import com.detoranja.models.SupplierModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SupplierAddressDto {
    @NotBlank
    private SupplierModel supplierModel;
    @NotBlank
    private long zip;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String district;
    @NotBlank
    private String state;
    private String reference;
}
