package com.inventoryManagement.service;

import com.inventoryManagement.entity.Depot;
import com.inventoryManagement.model.DepotRequest;
import com.inventoryManagement.repository.DepotRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DepotServiceImplTest {

    @InjectMocks
    private DepotServiceImpl depotService;

    @Mock
    private DepotRepository depotRepository;

    @Test
    public void it_should_create() {
        // GIVEN
        DepotRequest depotRequest = DepotRequest.builder()
                .address("address")
                .name("name")
                .build();


        // WHEN
        depotService.create(depotRequest);

        // THEN
        ArgumentCaptor<Depot> argumentCaptor = ArgumentCaptor.forClass(Depot.class);
        Mockito.verify(depotRepository).save(argumentCaptor.capture());
        Assertions.assertThat(argumentCaptor.getValue().getAddress()).isEqualTo("address");
    }

}