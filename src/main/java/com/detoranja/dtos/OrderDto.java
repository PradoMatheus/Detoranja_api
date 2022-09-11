package com.detoranja.dtos;

import com.detoranja.enums.StatusOrder;
import com.detoranja.models.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDto implements Serializable {
    @NotBlank
    private double total_value;
    @NotBlank
    private int quantity;
    @NotBlank
    private StatusOrder status;
    @NotBlank
    private List<OrderLogModel> orderLogModels;
    @NotBlank
    private LocalDateTime order_date;
    @NotBlank
    private ClientModel clientModel;
    @NotBlank
    private ClientAddressModel clientAddressModel;
    @NotBlank
    private OrderShippingModel orderShippingModel;
    @NotBlank
    private UserModel user_model;
}
