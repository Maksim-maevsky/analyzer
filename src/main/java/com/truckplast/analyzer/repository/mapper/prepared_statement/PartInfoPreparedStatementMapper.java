package com.truckplast.analyzer.repository.mapper.prepared_statement;


import com.truckplast.analyzer.entity.part.PartInfo;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class PartInfoPreparedStatementMapper {

    public void mapPreparedStatement(PreparedStatement ps, PartInfo partInfo) throws SQLException {

        ps.setObject(1, partInfo.getId());
        ps.setInt(2, partInfo.getCount());
        ps.setShort(3, partInfo.getPartStorageId());
        ps.setObject(4, partInfo.getPart().getId());
        ps.setTimestamp(5, Timestamp.valueOf(partInfo.getCreateDate()));

    }
}
