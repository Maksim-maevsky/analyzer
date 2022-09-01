package com.truckplast.analyzer.repository.impl;


import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.repository.TransactionPartRepository;
import com.truckplast.analyzer.repository.mapper.prepared_statement.PartInfoPreparedStatementMapper;
import com.truckplast.analyzer.repository.query.TransactionPartQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Data
@RequiredArgsConstructor
@Repository
public class TransactionPartRepositoryImpl implements TransactionPartRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PartInfoPreparedStatementMapper partInfoPreparedStatementMapper;


    @Override
    public int save(PartInfo part) {

        return jdbcTemplate.update(TransactionPartQuery.SAVE_TRANSACTION_PART_INFO_QUERY, part.getId(),
                part.getCount(), part.getPartStorageId(), part.getPart().getId(), part.getCreateDate());
    }

    @Override
    public int[] saveAll(List<PartInfo> partInfos) {

        return this.jdbcTemplate.batchUpdate(TransactionPartQuery.SAVE_TRANSACTION_PART_INFO_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                partInfoPreparedStatementMapper.mapPreparedStatement(ps, partInfos.get(i));

            }

            @Override
            public int getBatchSize() {
                return partInfos.size();
            }

        });
    }
}
