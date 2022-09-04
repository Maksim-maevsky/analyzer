package com.truckplast.analyzer.service.analysis;


import com.truckplast.analyzer.pojo.RefillResponse;
import com.truckplast.analyzer.pojo.RefillResult;

public interface PartAnalyzerService {

    RefillResult getRefillPartStorageInfo(RefillResponse refillResponseDto);
}
