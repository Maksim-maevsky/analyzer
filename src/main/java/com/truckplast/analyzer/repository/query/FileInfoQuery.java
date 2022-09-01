package com.truckplast.analyzer.repository.query;

public final class FileInfoQuery {

    private FileInfoQuery(){}

    public static final String SAVE_FAIL_INFO_QUERY = "INSERT INTO file_infos(id, file_name, extension, mail_id) VALUES(?,?,?,?)";

}
