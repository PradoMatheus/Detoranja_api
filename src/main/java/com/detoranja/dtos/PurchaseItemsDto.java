package com.detoranja.dtos;

import com.detoranja.models.ProductModel;
import com.detoranja.models.PurchaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class PurchaseItemsDto implements Serializable {
    @NotBlank
    private int quantity;
    @NotBlank
    private double price;
    private String observation;
    @NotBlank
    private ProductModel productModel;
    @NotBlank
    private PurchaseModel purchaseModel;
}
