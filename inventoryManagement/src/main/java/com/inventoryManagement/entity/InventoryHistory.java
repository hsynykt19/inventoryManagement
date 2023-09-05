package com.inventoryManagement.entity;

import com.inventoryManagement.enums.EnumActionType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INVENTORY_HISTORY")
public class InventoryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVENTORY_HISTORY")
    @SequenceGenerator(name = "SEQ_INVENTORY_HISTORY", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "DEPOT_ID")
    private Long depotId;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private EnumActionType type;

    @Column(name = "QUANTITY_CHANGE")
    private int quantityChange;

    @Column(name = "INSERT_DATE")
    private LocalDateTime insertDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
}
