//package com.inventoryManagement.service;
//
//import com.inventoryManagement.entity.*;
//import com.inventoryManagement.enums.EnumActionType;
//import com.inventoryManagement.enums.EnumStatus;
//import com.inventoryManagement.model.CategoryRequest;
//import com.inventoryManagement.model.ProductRequest;
//import com.inventoryManagement.model.InventoryResponse;
//import com.inventoryManagement.repository.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//@Slf4j
//@Service
//public class ProductServiceImpl2 implements ProductService {
//    private ProductRepository productRepository;
//    private CategoryRepository categoryRepository;
//    private DepotRepository depotRepository;
//    private DepotProductRepository depotProductRepository;
//    private InventoryHistoryRepository historyRepository;
//
//
//    public ProductServiceImpl2(ProductRepository productRepository, CategoryRepository categoryRepository, DepotRepository depotRepository, DepotProductRepository depotProductRepository, InventoryHistoryRepository historyRepository) {
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//        this.depotRepository = depotRepository;
//        this.depotProductRepository = depotProductRepository;
//        this.historyRepository = historyRepository;
//    }
//
//    @Override
//    public InventoryResponse adding(ProductRequest productRequest) {
//            Product  product = new Product();
//            product.setName(productRequest.getName());
//            product.setQuantity(productRequest.getQuantity());
//            product.setQuantity(productRequest.getCriticalThreshold());
//            Category category = new Category();
//            category.setName(productRequest.getCategory().getName());
//            product.setCategory(category);
//            categoryRepository.save(category);
//            productRepository.save(product);
//            Depot depot = depotRepository.findById(productRequest.getDepotId()).orElseThrow();
//            DepotProduct depotProduct=new DepotProduct();
//            depotProduct.setProductId(product.getId());
//            depotProduct.setDepotId(depot.getId());
//            depotProductRepository.save(depotProduct);
//        insertHistory(productRequest, product,EnumActionType.CREATE);
//        return getResponse(EnumStatus.SUCCESS, "100", "SUCCESS");
//    }
//
//    private void insertHistory(ProductRequest productRequest, Product product,EnumActionType type) {
//        InventoryHistory history=new InventoryHistory();
//        history.setProductId(product.getId());
//        history.setDepotId(productRequest.getDepotId());
//        history.setType(type);
//        history.setInsertDate(LocalDateTime.now());
//        history.setUpdateDate(LocalDateTime.now());
//        history.setQuantityChange(productRequest.getQuantity());
//        historyRepository.save(history);
//    }
//
//    @Override
//    public Product getProduct(String productId) {
//        return null;
//    }
//
//    @Override
//    public InventoryResponse updated(ProductRequest productRequest) {
//        Product product = productRepository.findByName(productRequest.getName()).orElseThrow();
//            if(product.getDepotProducts().stream().noneMatch(s->s.getDepotId()==productRequest.getDepotId())){
//                Depot depot = depotRepository.findById(productRequest.getDepotId()).orElseThrow();
//                DepotProduct depotProduct=new DepotProduct();
//                depotProduct.setProductId(product.getId());
//                depotProduct.setDepotId(depot.getId());
//                depotProductRepository.save(depotProduct);
//            }
//            product.setQuantity(product.getQuantity() + productRequest.getQuantity());
//            product.setUpdateDate(LocalDateTime.now());
//            productRepository.save(product);
//            insertHistory(productRequest, product,EnumActionType.UPDATE);
//        return getResponse(EnumStatus.SUCCESS, "100", "SUCCESS");
//
//
//    }
//
//    @Override
//    public InventoryResponse deleted(String productName) {
//        Product product = productRepository.findByName(productName).orElseThrow();
//        productRepository.delete(product);
//        ProductRequest request = getProductRequest(productName, product);
//        insertHistory(request, product,EnumActionType.DELETE);
//        return getResponse(EnumStatus.SUCCESS, "100", "SUCCESS");
//
//    }
//
//    private ProductRequest getProductRequest(String productName, Product product) {
//        ProductRequest request =new ProductRequest();
//        request.setName(productName);
//        request.setQuantity(product.getQuantity());
//        request.setCriticalThreshold(product.getCriticalThreshold());
//        CategoryRequest categoryRequest = new CategoryRequest();
//        categoryRequest.setName(product.getCategory().getName());
//        request.setCategory(categoryRequest);
//        request.setDepotId(product.getDepotProducts().stream().findFirst().get().getDepotId());
//        return request;
//    }
//
//    @Override
//    public InventoryResponse getOuted(ProductRequest productRequest) {
//
//        Product product = productRepository.findByName(productRequest.getName()).orElseThrow();
//        product.setQuantity(product.getQuantity() - productRequest.getQuantity());
//        product.setUpdateDate(LocalDateTime.now());
//            productRepository.save(product);
//            if(product.getQuantity()<product.getCriticalThreshold()){
//                log.info("Stok sayısı eşiğin altına düştü");
//            }
//        insertHistory(productRequest, product,EnumActionType.EXTRACT);
//
//        return getResponse(EnumStatus.SUCCESS, "100", "SUCCESS");
//    }
//
//
//    private InventoryResponse getResponse(EnumStatus status, String errorCode, String errorCodeDescription) {
//        InventoryResponse response=new InventoryResponse();
//       response.setStatus(status);
//       response.setErrorCode(errorCodeDescription);
//       response.setErrorCode(errorCode);
//       return response;
//    }
//}
