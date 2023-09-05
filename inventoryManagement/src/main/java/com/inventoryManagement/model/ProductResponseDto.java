
package com.inventoryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private List<CategoryDto> categories;
    private int quantity;
    private int criticalThreshold;
    private List<DepotProductDto> depotProducts;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryDto {
        private String name;
        private LocalDateTime insertDate;
        private LocalDateTime updateDate;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepotProductDto {
        private String name;
    }
}
