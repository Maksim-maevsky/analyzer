package com.truckplast.analyzer.service;


import com.truckplast.analyzer.dto.pojo.RefillRequestDto;
import com.truckplast.analyzer.dto.pojo.RefillResponseDto;

public interface PartService {

    RefillResponseDto getRefilledInfo(RefillRequestDto refillRequestDto);
}
