package com.inventoryManagement.model;

import lombok.*;


import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class DepotRequest implements Serializable {

    @NotNull(message = "should not be null or empty")
    private String name;

    @NotNull(message = "should not be null or empty")
    private String address;
    @NotNull(message = "should not be null or empty")
    private String region;
    @NotNull(message = "should not be null or empty")
    private String city;
}
