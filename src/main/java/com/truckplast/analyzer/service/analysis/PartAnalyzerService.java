package com.truckplast.analyzer.service.analysis;


import com.truckplast.analyzer.dto.pojo.RefillResponseDto;
import com.truckplast.analyzer.dto.pojo.RefillResultDto;

public interface PartAnalyzerService {

    RefillResultDto getRefillPartStorageInfo(RefillResponseDto refillResponseDto);
}
