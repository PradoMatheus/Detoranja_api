package com.detoranja.dtos;

import com.detoranja.models.ExchangeModel;
import com.detoranja.models.ProductModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class ExchangeItemsDto implements Serializable {
    @NotBlank
    private ExchangeModel exchangeModel;
    @NotBlank
    private ProductModel productModel;
    @NotBlank
    private int quantity;
    @NotBlank
    private double price;
}
