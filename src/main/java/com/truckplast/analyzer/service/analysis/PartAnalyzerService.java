package com.truckplast.analyzer.service.analysis;


import com.truckplast.analyzer.dto.RefillResponseDto;
import com.truckplast.analyzer.dto.RefillResultDto;

public interface PartAnalyzerService {

    RefillResultDto getRefillPartStorageInfo(RefillResponseDto refillResponseDto);
}
