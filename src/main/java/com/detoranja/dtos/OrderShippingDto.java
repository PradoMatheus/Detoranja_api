package com.detoranja.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class OrderShippingDto implements Serializable {
    @NotBlank
    private double value;
    @NotBlank
    private String tracking_code;
}
