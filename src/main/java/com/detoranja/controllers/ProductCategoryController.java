package com.detoranja.controllers;

import com.detoranja.dtos.ProductCategoryDto;
import com.detoranja.models.ProductCategoryModel;
import com.detoranja.services.ProductCategoryService;
import com.detoranja.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping(value = "/product_category")
public class ProductCategoryController {

    final ProductCategoryService productCategoryService;
    final UserService userService;

    public ProductCategoryController(ProductCategoryService productCategoryService, UserService userService) {
        this.productCategoryService = productCategoryService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProductCategory(@RequestBody @Valid ProductCategoryDto productCategoryDto,
                                                      @AuthenticationPrincipal User user) {
        var productCategoryModel = new ProductCategoryModel();
        if (productCategoryService.existsByName(productCategoryDto.getName()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use.");
        BeanUtils.copyProperties(productCategoryDto, productCategoryModel);
        productCategoryModel.setCreate_date(LocalDateTime.now(ZoneId.of("UTC")));
        productCategoryModel.setUserModel(userService.findByUsername(user.getUsername()));
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.save(productCategoryModel));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryModel>> getAllProductCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProductCategory(@PathVariable(value = "id") UUID id) {
        Optional<ProductCategoryModel> productCategoryModelOptional = productCategoryService.findById(id);
        if (!productCategoryModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Category not found.");
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductCategory(@PathVariable(value = "id") UUID id) {
        Optional<ProductCategoryModel> productCategoryModelOptional = productCategoryService.findById(id);
        if (!productCategoryModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Category not found.");
        productCategoryService.delete(productCategoryModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product Category deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductCategory(@RequestBody @Valid ProductCategoryDto productCategoryDto,
                                                        @PathVariable(value = "id") UUID id) {
        Optional<ProductCategoryModel> productCategoryModelOptional = productCategoryService.findById(id);
        if (!productCategoryModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Category not found.");
        var productCategoryModel = new ProductCategoryModel();
        BeanUtils.copyProperties(productCategoryDto, productCategoryModel);

        productCategoryModel.setId(productCategoryModelOptional.get().getId());
        productCategoryModel.setCreate_date(productCategoryModelOptional.get().getCreate_date());
        productCategoryModel.setUserModel(productCategoryModelOptional.get().getUserModel());
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.save(productCategoryModel));
    }
}
