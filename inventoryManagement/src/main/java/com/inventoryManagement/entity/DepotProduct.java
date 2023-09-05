package com.inventoryManagement.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEPOT_PRODUCT")
public class DepotProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEPOT_PRODUCT")
    @SequenceGenerator(name = "SEQ_DEPOT_PRODUCT", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "DEPOT_ID")
    private Long depotId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false, nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "DEPOT_ID", insertable = false, updatable = false, nullable = false)
    private Depot depot;
}
