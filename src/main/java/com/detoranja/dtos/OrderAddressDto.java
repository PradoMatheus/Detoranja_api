package com.detoranja.dtos;

import com.detoranja.models.OrderModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class OrderAddressDto implements Serializable {
    @NotBlank
    private OrderModel orderModel;
    @NotBlank
    private long zip;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String district;
    @NotBlank
    private String state;
    private String reference;
}
