package com.detoranja.controllers;

import com.detoranja.dtos.ProductDto;
import com.detoranja.models.ProductModel;
import com.detoranja.services.CompanyService;
import com.detoranja.services.ProductCategoryService;
import com.detoranja.services.ProductService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/product")
public class ProductController {

    final ProductService productService;
    final UserService userService;
    final ProductCategoryService productCategoryService;
    final CompanyService companyService;

    public ProductController(ProductService productService, UserService userService, ProductCategoryService productCategoryService, CompanyService companyService) {
        this.productService = productService;
        this.userService = userService;
        this.productCategoryService = productCategoryService;
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto, @AuthenticationPrincipal User user) {
        if (productCategoryService.findById(productDto.getProductCategory().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Category not found");
        if (companyService.findById(productDto.getCompanyModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Company not found.");
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setCreate_date(LocalDateTime.now(ZoneId.of("UTC")));
        productModel.setUserModel(userService.findByUsername(user.getUsername()));
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody @Valid ProductDto productDto,
                                                @PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        if (productCategoryService.findById(productDto.getProductCategory().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Category not found");
        if (companyService.findById(productDto.getCompanyModel().getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found: Company not found.");
        var productModel = productModelOptional.get();
        productModel.setName(productDto.getName());
        productModel.setDescription(productDto.getDescription());
        productModel.setProductCategory(productDto.getProductCategory());
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }
}
