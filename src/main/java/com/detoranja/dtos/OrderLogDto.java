package com.detoranja.dtos;

import com.detoranja.enums.StatusOrder;
import com.detoranja.models.OrderModel;
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
public class OrderLogDto implements Serializable {
    @NotBlank
    private OrderModel orderModel;
    @NotBlank
    private StatusOrder olderStatus;
    @NotBlank
    private StatusOrder newStatus;
    @NotBlank
    private UserModel userModel;
    @NotBlank
    private LocalDateTime date;
}