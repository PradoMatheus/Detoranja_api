package com.detoranja.dtos;

import com.detoranja.models.CartItemsModel;
import com.detoranja.models.ClientModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CartDto implements Serializable {
    @NotBlank
    private ClientModel clientModel;
    @NotBlank
    private int quantity;
    @NotBlank
    private double total_value;
}
