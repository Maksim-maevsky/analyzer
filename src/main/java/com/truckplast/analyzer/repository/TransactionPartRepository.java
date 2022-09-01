package com.truckplast.analyzer.repository;



import com.truckplast.analyzer.entity.part.PartInfo;

import java.util.List;

public interface TransactionPartRepository {

    int save(PartInfo part);

    int[] saveAll(List<PartInfo> parts);

}
