package com.truckplast.analyzer.controller;

import com.truckplast.analyzer.facade.AnalyzerFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
@Data
@RequiredArgsConstructor
public class AnalyzerController {

    private final AnalyzerFacade analyzerFacade;

    @Operation(summary = "Get difference between warehouses")
    @GetMapping("/warehouses/difference")
    public void differenceBetweenWarehouses() {

        analyzerFacade.differenceBetweenWarehouses();

    }
}
