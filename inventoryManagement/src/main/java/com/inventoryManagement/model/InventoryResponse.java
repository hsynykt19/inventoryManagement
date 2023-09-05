package com.inventoryManagement.model;

import com.inventoryManagement.enums.EnumStatus;
import lombok.Data;
import lombok.Generated;

import java.io.Serializable;

@Data
@Generated
public class InventoryResponse implements Serializable {
    EnumStatus status;
    String errorCode;
    String errorDescription;
}
