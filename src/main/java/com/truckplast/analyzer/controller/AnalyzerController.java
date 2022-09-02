package com.truckplast.analyzer.controller;

import com.truckplast.analyzer.dto.RefillResponseDto;
import com.truckplast.analyzer.dto.RefillResultDto;
import com.truckplast.analyzer.facade.AnalyzerFacade;
import com.truckplast.analyzer.facade.MailCheckerFacade;
import com.truckplast.analyzer.service.analysis.PartAnalyzerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

        analyzerFacade.differenceBetweenWarehouses();

    }

    @PostMapping
    public void sendRefilledInfo(@RequestBody RefillResultDto refillResultDto) {


    }
}
