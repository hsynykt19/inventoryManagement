package com.inventoryManagement.controller;

import com.inventoryManagement.model.InventoryResponse;
import com.inventoryManagement.model.ProductRequest;
import com.inventoryManagement.model.ProductResponseDto;
import com.inventoryManagement.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/inventory/products")
    public ResponseEntity<InventoryResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        InventoryResponse response = productService.create(productRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/inventory/products/filter")
    public ResponseEntity<ProductResponseDto> getProduct(@RequestParam(value = "productId") Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @PatchMapping(value = "/inventory/products/{productId}")
    public ResponseEntity<InventoryResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        InventoryResponse response = productService.update(productId, productRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "/inventory/products/out")
    public ResponseEntity<InventoryResponse> productOut(@Valid @RequestBody ProductRequest productRequest) {
        InventoryResponse response = productService.getOuted(productRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<InventoryResponse> product(@PathVariable(value = "productId") Long productId) {
        InventoryResponse response = productService.delete(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
