package com.detoranja.dtos;

import com.detoranja.enums.TypeUser;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private TypeUser typeUser;
}
