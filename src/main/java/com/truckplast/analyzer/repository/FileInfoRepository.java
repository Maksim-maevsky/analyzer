package com.truckplast.analyzer.repository;

import com.truckplast.analyzer.entity.FileInfo;

import java.util.List;

public interface FileInfoRepository {

    int[] saveAll(List<FileInfo> fileInfoList);

    int save(FileInfo fileInfo);

}
