package com.detoranja.dtos;

import com.detoranja.enums.TypePayment;
import com.detoranja.models.ClientModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ClientPaymentDto {
    private static final long serialVersionUID = 1;
    @NotBlank
    private ClientModel clientModel;
    @NotBlank
    private TypePayment typePayment;
    @NotBlank
    private String alias;
    @NotBlank
    private LocalDate date_validate;
    private String cvv;
    private long number_card;
    private String flag;
    private String name;
    private boolean favorite;
}
