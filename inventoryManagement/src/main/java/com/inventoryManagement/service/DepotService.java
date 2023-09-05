package com.inventoryManagement.service;

import com.inventoryManagement.entity.Depot;
import com.inventoryManagement.model.DepotRequest;

public interface DepotService {

    Depot create(DepotRequest depotRequest);
}
