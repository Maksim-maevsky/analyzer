package com.truckplast.analyzer.repository;


import com.truckplast.analyzer.entity.part.Brand;

import java.util.Optional;

public interface BrandRepository {

    Optional<Brand> getByName(String name);

}
