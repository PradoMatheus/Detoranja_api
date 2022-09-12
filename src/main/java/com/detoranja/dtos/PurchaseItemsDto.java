package com.detoranja.dtos;

import com.detoranja.models.ProductModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class PurchaseItemsDto implements Serializable {
    @NotBlank
    private UUID id;
    @NotBlank
    private int quantity;
    @NotBlank
    private double price;
    private String observation;
    @NotBlank
    private ProductModel product_model;
}
