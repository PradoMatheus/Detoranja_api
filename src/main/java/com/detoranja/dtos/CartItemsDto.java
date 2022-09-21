package com.detoranja.dtos;

import com.detoranja.models.CartModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class CartItemsDto implements Serializable {
    @NotBlank
    private CartModel cartModel;
    @NotBlank
    private int quantity;
}
