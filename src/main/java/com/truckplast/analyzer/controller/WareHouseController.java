package com.truckplast.analyzer.controller;

import com.truckplast.analyzer.dto.PartStorageDto;
import com.truckplast.analyzer.service.PartStorageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
@Data
@RequiredArgsConstructor
public class WareHouseController {

    private final PartStorageService partStorageService;

    @Operation(summary = "Get all warehouses")
    @GetMapping
    public List<PartStorageDto> differenceBetweenWarehouses() {

        return partStorageService.getPartStorageDtoList();
    }
}
