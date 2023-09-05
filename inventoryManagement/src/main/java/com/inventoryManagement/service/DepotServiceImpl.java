package com.inventoryManagement.service;

import com.inventoryManagement.entity.Depot;
import com.inventoryManagement.model.DepotRequest;
import com.inventoryManagement.repository.DepotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepotServiceImpl implements DepotService {
private final DepotRepository depotRepository;
    @Override
    public Depot create(DepotRequest depotRequest) {
        Depot depot = new Depot();
        depot.setAddress(depotRequest.getAddress());
        depot.setName(depotRequest.getName());
        depot.setCity(depotRequest.getCity());
        depot.setRegion(depotRequest.getRegion());
        depot.setUpdateDate(LocalDateTime.now());
        depot.setInsertDate(LocalDateTime.now());
        depotRepository.save(depot);
        log.info("Depot has been successfully created");
        return depot;
    }
}
