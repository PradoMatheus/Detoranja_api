package com.detoranja.dtos;

import com.detoranja.models.ProductModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class InventoryDto implements Serializable {
    @NotBlank
    private ProductModel productModel;
    private int balance;
    private int reserve;
}
