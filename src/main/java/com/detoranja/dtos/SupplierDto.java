package com.detoranja.dtos;

import com.detoranja.models.SupplierAddressModel;
import com.detoranja.models.SupplierContactModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class SupplierDto {
    @NotBlank
    private String social_reason;
    @NotBlank
    private String fantasy_name;
    @NotBlank
    private String cnpj;
    private long state_registration;
    private List<SupplierAddressModel> supplierAddressModels;
    private List<SupplierContactModel> supplierContactModels;
}
