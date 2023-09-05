package com.inventoryManagement.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Generated
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    @SequenceGenerator(name = "SEQ_PRODUCT", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "PRODUCT_NAME", unique = true, nullable = true)
    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> categories;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "CRITICAL_THRESHOLD")
    private int criticalThreshold;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DepotProduct> depotProducts;

    @Column(name = "INSERT_DATE")
    private LocalDateTime insertDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    public Product() {
        this.categories = new HashSet<>();
        this.depotProducts = new HashSet<>();
    }
}
