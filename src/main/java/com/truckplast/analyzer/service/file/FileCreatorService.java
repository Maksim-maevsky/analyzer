package com.truckplast.analyzer.service.file;


import com.truckplast.analyzer.dto.FileInfoDto;
import com.truckplast.analyzer.pojo.RefillResult;

public interface FileCreatorService {

    FileInfoDto getFile(RefillResult refillResultDto);

}
