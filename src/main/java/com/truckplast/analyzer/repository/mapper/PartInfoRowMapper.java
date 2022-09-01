package com.truckplast.analyzer.repository.mapper;


import com.truckplast.analyzer.entity.part.Brand;
import com.truckplast.analyzer.entity.part.Part;
import com.truckplast.analyzer.entity.part.PartInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartInfoRowMapper implements RowMapper<PartInfo> {


    @Override
    public PartInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        PartInfo partInfo = new PartInfo();

        partInfo.setId(rs.getObject(1, java.util.UUID.class));
        partInfo.setCreateDate(rs.getTimestamp(4).toLocalDateTime());
        partInfo.setCount(rs.getInt(2));
        partInfo.setPartStorageId(rs.getShort(3));

        Part part = new Part();

        part.setId(rs.getObject(5, java.util.UUID.class));
        part.setCreateDate(rs.getTimestamp(8).toLocalDateTime());
        part.setCode(rs.getString(6));
        part.setDescription(rs.getString(7));

        Brand brand = new Brand();

        brand.setId(rs.getInt(9));
        brand.setName(rs.getString(10));

        part.setBrand(brand);
        partInfo.setPart(part);

        return partInfo;
    }
}
