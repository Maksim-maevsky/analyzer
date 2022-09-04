package com.truckplast.analyzer.service;


import com.truckplast.analyzer.pojo.RefillRequest;
import com.truckplast.analyzer.pojo.RefillResponse;

public interface PartService {

    RefillResponse getRefilledInfo(RefillRequest refillRequest);
}
