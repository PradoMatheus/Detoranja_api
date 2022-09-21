package com.detoranja.controllers;

import com.detoranja.dtos.CouponDto;
import com.detoranja.models.CouponModel;
import com.detoranja.services.CompanyService;
import com.detoranja.services.CouponService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping(value = "/coupon")
public class CouponController {

    final CouponService couponService;
    final CompanyService companyService;

    public CouponController(CouponService couponService, CompanyService companyService) {
        this.couponService = couponService;
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCoupon(@RequestBody @Valid CouponDto couponDto) {
        if (couponService.existByName(couponDto.getName()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use.");
        else if (companyService.findById(couponDto.getCompanyModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Company not found");
        var couponModel = new CouponModel();
        BeanUtils.copyProperties(couponDto, couponModel);
        couponModel.setCreate_date(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(couponService.save(couponModel));
    }

    @GetMapping
    public ResponseEntity<List<CouponModel>> getAllCoupon() {
        return ResponseEntity.status(HttpStatus.OK).body(couponService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneCoupon(@PathVariable(value = "id") UUID id) {
        var couponModelOptional = couponService.findById(id);
        if (!couponModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coupon not found");
        return ResponseEntity.status(HttpStatus.OK).body(couponModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCoupon(@PathVariable(value = "id") UUID id) {
        var couponModelOptional = couponService.findById(id);
        if (!couponModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coupon not found");
        couponService.delete(couponModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Coupon deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody @Valid CouponDto couponDto, @PathVariable(value = "id") UUID id) {
        var couponModelOptional = couponService.findById(id);
        if (!couponModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coupon not found");
        else if (companyService.findById(couponDto.getCompanyModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Company not found");
        var couponModel = couponModelOptional.get();
        BeanUtils.copyProperties(couponDto, couponModel);
        return ResponseEntity.status(HttpStatus.OK).body(couponService.save(couponModel));
    }
}
