package com.detoranja.dtos;

import com.detoranja.models.SupplierModel;
import com.detoranja.models.UserModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PurchaseDto implements Serializable {
    @NotBlank
    private int quantity;
    @NotBlank
    private String fiscal_note;
    @NotBlank
    private LocalDateTime create_date;
    @NotBlank
    private double price;
    @NotBlank
    private UserModel user_model;
    @NotBlank
    private SupplierModel supplierModel;
}
