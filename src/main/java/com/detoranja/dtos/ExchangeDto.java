package com.detoranja.dtos;

import com.detoranja.enums.StatusExchange;
import com.detoranja.models.CompanyModel;
import com.detoranja.models.OrderModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExchangeDto implements Serializable {
    @NotBlank
    private OrderModel orderModel;
    @NotBlank
    private CompanyModel companyModel;
    @NotBlank
    private StatusExchange status;
}
