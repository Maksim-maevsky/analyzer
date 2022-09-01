package com.truckplast.analyzer.repository.query;

public final class TransactionPartQuery {

    private TransactionPartQuery(){}

    public static final String SAVE_TRANSACTION_PART_INFO_QUERY = "INSERT INTO transaction_part_infos(id, count, part_storage_id, part_id, create_date) VALUES(?,?,?,?,?)";

}
