package com.detoranja.dtos;

import com.detoranja.models.ClientModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ClientContactDto {
    @NotBlank
    private ClientModel clientModel;
    @NotBlank
    private String name;
    private long cellphone;
    private long phone;
}
