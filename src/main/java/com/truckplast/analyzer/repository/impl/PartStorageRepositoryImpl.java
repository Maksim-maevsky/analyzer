package com.truckplast.analyzer.repository.impl;


import com.truckplast.analyzer.entity.part.PartStorage;
import com.truckplast.analyzer.repository.PartStorageRepository;
import com.truckplast.analyzer.repository.mapper.PartStorageRowMapper;
import com.truckplast.analyzer.repository.mapper.prepared_statement.PartInfoPreparedStatementMapper;
import com.truckplast.analyzer.repository.query.PartStorageQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@RequiredArgsConstructor
@Repository
public class PartStorageRepositoryImpl implements PartStorageRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PartInfoPreparedStatementMapper partInfoPreparedStatementMapper;


    @Override
    public List<PartStorage> getAll() {

        return jdbcTemplate.query(PartStorageQuery.GET_ALL, new PartStorageRowMapper());
    }

}
