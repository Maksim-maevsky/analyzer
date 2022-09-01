package com.truckplast.analyzer.repository.impl;

import com.truckplast.analyzer.entity.MailInfo;
import com.truckplast.analyzer.repository.MailInfoRepository;
import com.truckplast.analyzer.repository.mapper.prepared_statement.MailInfoPreparedStatementMapper;
import com.truckplast.analyzer.repository.query.MailInfoQuery;
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
public class MailInfoRepositoryImpl implements MailInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final MailInfoPreparedStatementMapper mailInfoPreparedStatementMapper;


    @Override
    public int[] saveAll(List<MailInfo> mailInfoList) {

        return this.jdbcTemplate.batchUpdate(MailInfoQuery.SAVE_MAIL_INFO_QUERY, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {

                mailInfoPreparedStatementMapper.mapPreparedStatement(ps, mailInfoList.get(i));

            }

            @Override
            public int getBatchSize() {
                return mailInfoList.size();
            }

        });
    }
}
