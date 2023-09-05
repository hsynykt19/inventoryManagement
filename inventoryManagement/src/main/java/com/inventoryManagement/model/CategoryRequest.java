package com.inventoryManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Generated
public class CategoryRequest implements Serializable {
    @NotNull(message = "should not be null or empty")
    @JsonProperty("name")
    private String name;

}
