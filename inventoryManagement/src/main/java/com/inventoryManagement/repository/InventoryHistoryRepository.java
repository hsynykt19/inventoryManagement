package com.inventoryManagement.repository;


import com.inventoryManagement.entity.InventoryHistory;
import com.inventoryManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, Long> {


}
