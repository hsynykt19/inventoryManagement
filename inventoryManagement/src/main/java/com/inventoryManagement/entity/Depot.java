package com.inventoryManagement.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEPOT")
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEPOT")
    @SequenceGenerator(name = "SEQ_DEPOT", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "DEPOT_NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "REGION")
    private String region;


    @Column(name = "CITY")
    private String city;

    @Column(name = "INSERT_DATE")
    private LocalDateTime insertDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
}
