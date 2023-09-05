package com.inventoryManagement.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inventoryManagement.entity.Category;



import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class ProductRequest implements Serializable {

    @NotNull(message = "should not be null or empty")
    private String name;

    @NotNull(message = "should not be null or empty")
    private String categoryName;

    @NotNull(message = "should not be null or empty")
    private Long depotId;

    @NotNull(message = "should not be null or empty")
    private int quantity;

    @NotNull(message = "should not be null or empty")
    private int criticalThreshold;
}
