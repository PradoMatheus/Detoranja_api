package com.detoranja.dtos;

import com.detoranja.enums.TypeMovement;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class InventoryMovementDto implements Serializable {
    @NotBlank
    private int quantity;
    @NotBlank
    private TypeMovement type;
    @NotBlank
    private LocalDateTime date_movement;
}
