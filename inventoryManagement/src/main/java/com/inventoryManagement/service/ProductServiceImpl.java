package com.inventoryManagement.service;

import com.inventoryManagement.entity.*;
import com.inventoryManagement.enums.EnumActionType;
import com.inventoryManagement.enums.EnumStatus;
import com.inventoryManagement.model.*;
import com.inventoryManagement.repository.CategoryRepository;
import com.inventoryManagement.repository.DepotRepository;
import com.inventoryManagement.repository.InventoryHistoryRepository;
import com.inventoryManagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DepotRepository depotRepository;
    private final InventoryHistoryRepository historyRepository;

    @Override
    public InventoryResponse create(ProductRequest productRequest) {
        Product product = new Product();
        Depot depot = depotRepository.findById(productRequest.getDepotId()).orElseThrow();

        product.setName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());
        product.setQuantity(productRequest.getCriticalThreshold());
        product.setUpdateDate(LocalDateTime.now());
        product.setInsertDate(LocalDateTime.now());
        productRepository.save(product);

        Category category = categoryRepository.findFirstByNameIgnoreCase(productRequest.getCategoryName()).orElseGet(() -> Category.builder()
                .name(productRequest.getCategoryName())
                .insertDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .productId(product.getId())
                .build());

        DepotProduct depotProduct = DepotProduct.builder()
                .depotId(depot.getId())
                .productId(product.getId())
                .build();
        product.getCategories().add(category);
        product.getDepotProducts().add(depotProduct);
        productRepository.save(product);

        insertHistory(productRequest, product, EnumActionType.CREATE);
        return getResponse(EnumStatus.SUCCESS, "100", "SUCCESS");
    }

    @Override
    public ProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return null;
        }
        return ProductResponseDto.builder()
                .id(product.getId())
                .quantity(product.getQuantity())
                .criticalThreshold(product.getCriticalThreshold())
                .insertDate(product.getInsertDate())
                .updateDate(product.getUpdateDate())
                .depotProducts(product.getDepotProducts()
                        .stream()
                        .map(m ->
                                ProductResponseDto.DepotProductDto.builder()
                                        .name(m.getDepot().getName())
                                        .build()).collect(Collectors.toList()))
                .categories(product.getCategories().stream()
                        .map(m -> ProductResponseDto.CategoryDto.builder()
                                .name(m.getName())
                                .updateDate(m.getUpdateDate())
                                .insertDate(m.getInsertDate())
                                .build()).collect(Collectors.toList()))
                .build();
    }

    @Override
    public InventoryResponse update(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setQuantity(product.getQuantity() + productRequest.getQuantity());
        product.setUpdateDate(LocalDateTime.now());
        productRepository.save(product);
        insertHistory(productRequest, product, EnumActionType.UPDATE);
        return getResponse(EnumStatus.SUCCESS, "100", "SUCCESS");
    }

    @Override
    public InventoryResponse delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        productRepository.delete(product);
        ProductRequest request = getProductRequest(product.getName(), product);
        insertHistory(request, product, EnumActionType.DELETE);
        return getResponse(EnumStatus.SUCCESS, "100", "SUCCESS");
    }

    @Override
    public InventoryResponse getOuted(ProductRequest productRequest) {

        Product product = productRepository.findByName(productRequest.getName()).orElseThrow();
        product.setQuantity(product.getQuantity() - productRequest.getQuantity());
        product.setUpdateDate(LocalDateTime.now());
        productRepository.save(product);
        if (product.getQuantity() < product.getCriticalThreshold()) {
            log.info("Product quantity is now lower than critical threshold");
        }
        insertHistory(productRequest, product, EnumActionType.EXTRACT);
        return getResponse(EnumStatus.SUCCESS, "100", "SUCCESS");
    }

    private void insertHistory(ProductRequest productRequest, Product product, EnumActionType type) {
        InventoryHistory history = new InventoryHistory();
        history.setProductId(product.getId());
        history.setDepotId(productRequest.getDepotId());
        history.setType(type);
        history.setInsertDate(LocalDateTime.now());
        history.setUpdateDate(LocalDateTime.now());
        history.setQuantityChange(productRequest.getQuantity());
        historyRepository.save(history);
    }

    private InventoryResponse getResponse(EnumStatus status, String errorCode, String errorCodeDescription) {
        InventoryResponse response = new InventoryResponse();
        response.setStatus(status);
        response.setErrorDescription(errorCodeDescription);
        response.setErrorCode(errorCode);
        return response;
    }

    private ProductRequest getProductRequest(String productName, Product product) {
        ProductRequest request = new ProductRequest();
        request.setName(productName);
        request.setQuantity(product.getQuantity());
        request.setCriticalThreshold(product.getCriticalThreshold());
        request.setDepotId(product.getDepotProducts().stream().findFirst().get().getDepotId());
        return request;
    }
}
