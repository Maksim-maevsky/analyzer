package com.truckplast.analyzer.repository.query;

public final class PartQuery {

    private PartQuery(){}

    public static final String SAVE_PART_QUERY = "INSERT INTO parts(id, code, description, brand_id, create_date) VALUES(?,?,?,?,?)";

    public static final String GET_PART_BY_CODE_AND_STORAGE_NAME = "SELECT parts.id" +
            " FROM parts LEFT JOIN brands b on parts.brand_id = b.id where parts.code = '%s' AND b.name = '%s'";

}
