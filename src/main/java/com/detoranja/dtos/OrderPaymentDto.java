package com.detoranja.dtos;

import com.detoranja.enums.TypePayment;
import com.detoranja.models.OrderModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class OrderPaymentDto implements Serializable {
    @NotBlank
    private OrderModel orderModel;
    @NotBlank
    private TypePayment typePayment;
    @NotBlank
    private LocalDate date_validate;
    @NotBlank
    private String cvv;
    @NotBlank
    private long number_card;
    @NotBlank
    private String flag;
    @NotBlank
    private String name;
}
