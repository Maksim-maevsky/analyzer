package com.truckplast.analyzer.repository.mapper.prepared_statement;


import com.truckplast.analyzer.entity.MailInfo;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class MailInfoPreparedStatementMapper {

    public void mapPreparedStatement(PreparedStatement ps, MailInfo mailInfo) throws SQLException {

        ps.setObject(1, mailInfo.getId());
        ps.setString(2, mailInfo.getSubject());
        ps.setString(3, mailInfo.getFrom());
        ps.setTimestamp(4, Timestamp.valueOf(mailInfo.getDateTime()));

    }
}
