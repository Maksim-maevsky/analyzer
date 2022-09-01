package com.truckplast.analyzer.service.file;


import com.truckplast.analyzer.dto.FileInfoDto;
import com.truckplast.analyzer.dto.RefillResultDto;

public interface FileCreatorService {

    FileInfoDto getFile(RefillResultDto refillResultDto);

}
