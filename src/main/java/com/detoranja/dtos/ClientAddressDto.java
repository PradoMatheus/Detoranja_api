package com.detoranja.dtos;

import com.detoranja.models.ClientModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class ClientAddressDto {
    @NotBlank
    private ClientModel clientModel;
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
    private boolean favorite;
}
