package com.truckplast.analyzer.repository;



import com.truckplast.analyzer.entity.part.Part;

import java.util.Optional;
import java.util.UUID;

public interface PartRepository {

    int save(Part part);

    Optional<UUID> getIdByCodeAndBrand(String code, String brand);

}
