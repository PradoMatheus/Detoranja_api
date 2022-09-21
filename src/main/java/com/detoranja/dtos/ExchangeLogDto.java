package com.detoranja.dtos;

import com.detoranja.enums.StatusExchange;
import com.detoranja.models.ExchangeModel;
import com.detoranja.models.UserModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ExchangeLogDto implements Serializable {
    @NotBlank
    private ExchangeModel exchangeModel;
    @NotBlank
    private StatusExchange status;
}