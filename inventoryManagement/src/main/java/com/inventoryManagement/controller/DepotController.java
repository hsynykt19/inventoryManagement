package com.inventoryManagement.controller;

import com.inventoryManagement.entity.Depot;
import com.inventoryManagement.model.DepotRequest;
import com.inventoryManagement.service.DepotService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api
@RestController
@RequiredArgsConstructor
public class DepotController {

    private final DepotService depotService;

    @PostMapping(value = "/depots")
    public ResponseEntity<Object> create(@Valid @RequestBody DepotRequest depotRequest) {
        Depot depot=depotService.create(depotRequest);
        return new ResponseEntity<>(depot, HttpStatus.OK);
    }
}
