package com.truckplast.analyzer.repository.impl;


import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.repository.PartInfoRepository;
import com.truckplast.analyzer.repository.mapper.PartInfoRowMapper;
import com.truckplast.analyzer.repository.mapper.prepared_statement.PartInfoPreparedStatementMapper;
import com.truckplast.analyzer.repository.query.PartInfoQuery;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Repository
public class PartInfoRepositoryImpl implements PartInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PartInfoPreparedStatementMapper partInfoPreparedStatementMapper;


    @Override
    public void delete(short storagePartId) {

        Object[] args = new Object[]{storagePartId};
        jdbcTemplate.update(PartInfoQuery.DELETE_PARTS_INFO_BY_STORAGE_ID, args);

    }

    @Override
    public int save(PartInfo part) {

        return jdbcTemplate.update(PartInfoQuery.SAVE_PART_INFO_QUERY, part.getId(), part.getCount(), part.getPartStorageId(), part.getPart().getId(), part.getCreateDate());
    }

    @Override
    public int[] saveAll(List<PartInfo> partInfoList) {

        return this.jdbcTemplate.batchUpdate(PartInfoQuery.SAVE_PART_INFO_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                partInfoPreparedStatementMapper.mapPreparedStatement(ps, partInfoList.get(i));

            }

            @Override
            public int getBatchSize() {
                return partInfoList.size();
            }

        });
    }

    @Override
    public List<PartInfo> getAllByPartStorageName(Set<String> partStorageNameSet) {
        String inSql = String.join(",", Collections.nCopies(partStorageNameSet.size(), "?"));


        return jdbcTemplate.query(String.format(PartInfoQuery.GET_PARTS_INFO_BY_STORAGE_NAME, inSql), partStorageNameSet.toArray(), new PartInfoRowMapper());

    }
}
