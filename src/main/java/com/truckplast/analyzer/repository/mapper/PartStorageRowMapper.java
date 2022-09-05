package com.truckplast.analyzer.repository.mapper;


import com.truckplast.analyzer.entity.part.PartStorage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartStorageRowMapper implements RowMapper<PartStorage> {


    @Override
    public PartStorage mapRow(ResultSet rs, int rowNum) throws SQLException {

        PartStorage partStorage = new PartStorage();

        partStorage.setId(rs.getShort("id"));
        partStorage.setName(rs.getString("name"));

        return partStorage;
    }
}
