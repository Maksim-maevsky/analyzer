package com.truckplast.analyzer.repository.mapper.prepared_statement;


import com.truckplast.analyzer.entity.FileInfo;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class FileInfoPreparedStatementMapper {

    public void mapPreparedStatement(PreparedStatement ps, FileInfo fileInfo) throws SQLException {

        ps.setObject(1, fileInfo.getId());
        ps.setString(2, fileInfo.getFileName());
        ps.setString(3, fileInfo.getExtension());
        ps.setObject(4, fileInfo.getMailInfoId());

    }
}
