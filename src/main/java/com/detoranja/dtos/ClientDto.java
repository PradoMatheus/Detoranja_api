package com.detoranja.dtos;

import com.detoranja.models.ClientAddressModel;
import com.detoranja.models.ClientContactModel;
import com.detoranja.models.UserModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClientDto {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private long cpf;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_data;
    private List<ClientAddressModel> clientAddressModels;
    private List<ClientContactModel> clientContactModels;
    private UserModel userModel;
    private boolean enabled;
}
