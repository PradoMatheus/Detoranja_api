package com.detoranja.dtos;

import javax.validation.constraints.NotBlank;

public class AdministratorDto {

    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
