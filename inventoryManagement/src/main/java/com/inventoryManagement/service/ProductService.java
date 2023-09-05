package com.inventoryManagement.service;

import com.inventoryManagement.model.ProductRequest;
import com.inventoryManagement.model.InventoryResponse;
import com.inventoryManagement.model.ProductResponseDto;

public interface ProductService {

    InventoryResponse create(ProductRequest productRequest);

    ProductResponseDto getProduct(Long productId);

    InventoryResponse update(Long productId, ProductRequest productRequest);

    InventoryResponse delete(Long productId);

    InventoryResponse getOuted(ProductRequest productRequest);
}
