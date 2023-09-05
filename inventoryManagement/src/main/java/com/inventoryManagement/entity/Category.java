package com.inventoryManagement.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORY")
    @SequenceGenerator(name = "SEQ_CATEGORY", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CATEGORY_NAME")
    private String name;

    @Column(name = "INSERT_DATE")
    private LocalDateTime insertDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false, nullable = false)
    private Product product;

    @Column(name = "PRODUCT_ID")
    public Long productId;

}
