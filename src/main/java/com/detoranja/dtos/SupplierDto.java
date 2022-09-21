package com.detoranja.dtos;

import com.detoranja.models.CompanyModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SupplierDto {
    @NotBlank
    private String social_reason;
    @NotBlank
    private String fantasy_name;
    @NotBlank
    private String cnpj;
    @NotBlank
    private CompanyModel companyModel;
    private long state_registration;
}
