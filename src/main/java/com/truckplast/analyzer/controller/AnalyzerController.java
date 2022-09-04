package com.truckplast.analyzer.controller;

import com.truckplast.analyzer.facade.AnalyzerFacade;
import com.truckplast.analyzer.pojo.RefillRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/analysis")
@Data
@RequiredArgsConstructor
public class AnalyzerController {

    private final AnalyzerFacade analyzerFacade;

    @Operation(summary = "Get difference between warehouses")
    @GetMapping("/warehouses/difference")
    public void differenceBetweenWarehouses() {

        analyzerFacade.differenceBetweenWarehouses(getRefillRequest());

    }

    private RefillRequest getRefillRequest(){

        Set<String> target = Set.of("PLASTIC", "TANGDE");
        Set<String> current = Set.of("MIKHNEVO");

        RefillRequest refillRequest = new RefillRequest();
        refillRequest.setCurrentPartStorageNameSet(current);
        refillRequest.setTargetPartStorageNameSet(target);

        return refillRequest;
    }
}
