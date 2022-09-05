package com.truckplast.analyzer.repository.mapper;


import com.truckplast.analyzer.entity.part.Part;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartRowMapper implements RowMapper<Part> {


    @Override
    public Part mapRow(ResultSet rs, int rowNum) throws SQLException {

        Part part = new Part();

        part.setId(rs.getObject("id", java.util.UUID.class));
        part.setCode(rs.getString("code"));
        part.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());

        return part;
    }
}
