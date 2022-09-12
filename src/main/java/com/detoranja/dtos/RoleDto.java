package com.detoranja.dtos;

import com.detoranja.enums.RoleName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class RoleDto implements Serializable {
    @NotBlank
    private RoleName roleName;
}
