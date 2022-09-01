package com.truckplast.analyzer.repository.query;

public final class PartInfoQuery {

    private PartInfoQuery() {
    }

    public static final String SAVE_PART_INFO_QUERY = "INSERT INTO part_infos (id, count, part_storage_id, part_id, create_date) VALUES(?,?,?,?,?)";
    public static final String DELETE_PARTS_INFO_BY_STORAGE_ID = "DELETE FROM part_infos WHERE part_storage_id = ?";

    public static final String GET_PARTS_INFO_BY_STORAGE_NAME = "SELECT part_infos.id, part_infos.count," +
            " part_infos.part_storage_id, part_infos.create_date," +
            " p.id, p.code, p.description, p.create_date, b.id, b.name FROM part_infos" +
            " LEFT JOIN parts p ON part_infos.part_id = p.id LEFT JOIN brands b on p.brand_id = b.id" +
            " LEFT JOIN part_storages ps on part_infos.part_storage_id = ps.id where ps.name IN (%s)";

}
