package com.inventoryManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryManagement.Util.JsonUtil;
import com.inventoryManagement.entity.Category;
import com.inventoryManagement.entity.Depot;
import com.inventoryManagement.entity.Product;
import com.inventoryManagement.model.InventoryResponse;
import com.inventoryManagement.model.ProductRequest;
import com.inventoryManagement.model.ProductResponseDto;
import com.inventoryManagement.repository.CategoryRepository;
import com.inventoryManagement.repository.DepotRepository;
import com.inventoryManagement.repository.InventoryHistoryRepository;
import com.inventoryManagement.repository.ProductRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    MvcResult result = null;
    ProductResponseDto productResponseDto = null;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    CategoryRepository categoryRepository;
    @MockBean
    DepotRepository depotRepository;
    @MockBean
    InventoryHistoryRepository historyRepository;
    @Autowired
    private ProductController productController;

    @Test
    public void ProductRequestCheck() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/inventory/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.objectToJson(productRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ProductRequestCheckNameIsNull() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(null);
        productRequest.setDepotId(5L);
        productRequest.setQuantity(5);
        productRequest.setCategoryName("TV");
        productRequest.setCriticalThreshold(20);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/inventory/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.objectToJson(productRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ProductRequestCheckDepoId() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Samsung");
        productRequest.setDepotId(null);
        productRequest.setQuantity(5);
        productRequest.setCategoryName("TV");
        productRequest.setCriticalThreshold(20);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/inventory/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.objectToJson(productRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ProductRequestCheckCategoryName() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Samsung");
        productRequest.setDepotId(5L);
        productRequest.setQuantity(5);
        productRequest.setCategoryName(null);
        productRequest.setCriticalThreshold(20);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/inventory/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.objectToJson(productRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ProductRequestCreate() throws Exception {
        ProductRequest productRequest = getProductRequest();
        Depot depot = createDepot(productRequest.getDepotId());
        Category category = createCategory(productRequest);
        when(depotRepository.findById(any())).thenReturn(Optional.of(depot));
        when(categoryRepository.findFirstByNameIgnoreCase(any())).thenReturn(Optional.of(category));
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/inventory/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.objectToJson(productRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void ProductRequestFilter() throws Exception {
        ProductRequest productRequest = getProductRequest();
        Depot depot = createDepot(productRequest.getDepotId());
        Category category = createCategory(productRequest);
        Product product = new Product();
        productRequest.setName("Samsung");
        productRequest.setDepotId(depot.getId());
        productRequest.setQuantity(productRequest.getQuantity());
        productRequest.setCategoryName(category.getName());
        product.setId(5L);
        productRequest.setCriticalThreshold(productRequest.getCriticalThreshold());
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
//        when(categoryRepository.findFirstByNameIgnoreCase(any())).thenReturn(Optional.of(category));
        this.result = this.mockMvc.perform(get("/inventory/products/filter").param("productId", "5l").header(HttpHeaders.AUTHORIZATION, ""))
                .andExpect(status().isOk()).andReturn();
        this.productResponseDto = objectMapper.readValue(result.getResponse().getContentAsString(),
                ProductResponseDto.class);

    }

    private ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Samsung");
        productRequest.setDepotId(5L);
        productRequest.setQuantity(5);
        productRequest.setCategoryName("TV");
        productRequest.setCriticalThreshold(20);
        productRequest.setQuantity(22);
        return productRequest;
    }

    private Category createCategory(ProductRequest productRequest) {
        Category category = new Category();
        category.setName(productRequest.getCategoryName());
        return category;
    }

    private Depot createDepot(Long depotId) {
        Depot depot = new Depot();
        depot.setName("maltepe-depo");
        depot.setRegion("marmara");
        depot.setAddress("maltepe-küçükyalı");
        depot.setCity("istanbul");
        depot.setInsertDate(LocalDateTime.now());
        depot.setUpdateDate(LocalDateTime.now());
        depot.setId(depotId);
        return depot;
    }

}
