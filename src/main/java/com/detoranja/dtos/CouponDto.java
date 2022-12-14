package com.detoranja.dtos;

import com.detoranja.enums.TypeCoupon;
import com.detoranja.models.CompanyModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class CouponDto {
    @NotBlank
    private String name;
    private String description;
    private TypeCoupon typeCoupon;
    @NotBlank
    private CompanyModel companyModel;
    @NotBlank
    private double percentage;
    @NotBlank
    private double value;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_validate;
    @NotBlank
    private boolean enable;
}
