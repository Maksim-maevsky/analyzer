package com.truckplast.analyzer.repository.impl;


import com.truckplast.analyzer.entity.part.Brand;
import com.truckplast.analyzer.repository.BrandRepository;
import com.truckplast.analyzer.repository.mapper.BrandRowMapper;
import com.truckplast.analyzer.repository.query.BrandQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Repository
public class BrandRepositoryImpl implements BrandRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Brand> getByName(String name) {


        try {

             return Optional.of(jdbcTemplate.queryForObject(String.format(BrandQuery.GET_BY_NAME), new BrandRowMapper(), name));

        } catch (EmptyResultDataAccessException exception) {

            return Optional.empty();
        }
    }
}
