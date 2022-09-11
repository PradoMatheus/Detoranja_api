package com.detoranja.dtos;

import com.detoranja.models.OrderModel;
import com.detoranja.models.ProductModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class OrderItemsDto implements Serializable {
    @NotBlank
    private OrderModel orderModel;
    @NotBlank
    private ProductModel productModel;
    @NotBlank
    private int quantity;
    @NotBlank
    private double price;
}
