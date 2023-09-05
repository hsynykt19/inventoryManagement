package com.inventoryManagement.repository;


import com.inventoryManagement.entity.Category;
import com.inventoryManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findFirstByNameIgnoreCase(String name);
}
