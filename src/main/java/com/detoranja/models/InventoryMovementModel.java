package com.detoranja.models;

import com.detoranja.enums.TypeMovement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_inventory_movement")
public class InventoryMovementModel implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnoreProperties("inventoryMovementModels")
    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryModel inventoryModel;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private TypeMovement type;
    @Column(nullable = false)
    private LocalDateTime date_movement;
}
