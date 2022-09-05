package com.truckplast.analyzer.repository;

import com.truckplast.analyzer.entity.part.PartStorage;

import java.util.List;

public interface PartStorageRepository {

    List<PartStorage> getAll();
}
