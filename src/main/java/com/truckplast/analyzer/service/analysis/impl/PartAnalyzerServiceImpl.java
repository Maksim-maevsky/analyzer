package com.truckplast.analyzer.service.analysis.impl;


import com.truckplast.analyzer.dto.pojo.RefillResponseDto;
import com.truckplast.analyzer.dto.pojo.RefillResultDto;
import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.service.analysis.PartAnalyzerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Data
@Slf4j
public class PartAnalyzerServiceImpl implements PartAnalyzerService {

    @Override
    public RefillResultDto getRefillPartStorageInfo(RefillResponseDto refillResponseDto) {

        List<PartInfo> resultPartInfoDtoList = getResultPartDtoList(refillResponseDto);

        return  getRefillResultDto(refillResponseDto, resultPartInfoDtoList);
    }

    private RefillResultDto getRefillResultDto(RefillResponseDto refillResponseDto, List<PartInfo> resultPartInfoDtoList) {

        log.info("Get refillResultDto");

        RefillResultDto refillResultDto = new RefillResultDto();

        Set<String> currentPartStorageNameSet = refillResponseDto.getCurrentPartStorageInfoDto().getPartStorageNameSet();
        Set<String> targetPartStorageNameSet = refillResponseDto.getTargetPartStorageInfoDto().getPartStorageNameSet();

        refillResultDto.setResultPartInfoDtoList(resultPartInfoDtoList);
        refillResultDto.setCurrentPartStorageName(currentPartStorageNameSet);
        refillResultDto.setTargetPartStorageName(targetPartStorageNameSet);

        return refillResultDto;
    }

    private List<PartInfo> getResultPartDtoList(RefillResponseDto refillResponseDto) {

        log.info("try to compare target and current list and get result list.");

        List<PartInfo> resultPartInfoDtoList = new ArrayList<>();
        refillResponseDto
                .getCurrentPartStorageInfoDto()
                .getPartList()
                .parallelStream().forEach(currentPart -> iterateByTargetPartDtoListAndSetToResultPartDtoList(refillResponseDto, resultPartInfoDtoList, currentPart));

        return resultPartInfoDtoList;
    }

    private void iterateByTargetPartDtoListAndSetToResultPartDtoList(RefillResponseDto refillResponseDto,
                                                                     List<PartInfo> resultPartInfoDtoList,
                                                                     PartInfo currentPartInfo) {

        int matchCount = 0;

        for(PartInfo targetPartInfo : refillResponseDto.getTargetPartStorageInfoDto().getPartList()){

            if (currentPartInfo.getPart().getCode().equals(targetPartInfo.getPart().getCode())){

                matchCount++;

            }
        }

        if(matchCount == 0){

            resultPartInfoDtoList.add(currentPartInfo);

        }
    }
}
