package com.truckplast.analyzer.service;


import com.truckplast.analyzer.dto.RefillRequestDto;
import com.truckplast.analyzer.dto.RefillResponseDto;

public interface PartService {

    RefillResponseDto getRefilledInfo(RefillRequestDto refillRequestDto);
}
