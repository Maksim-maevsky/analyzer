package com.truckplast.analyzer.service.analysis.impl;


import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.pojo.RefillResponse;
import com.truckplast.analyzer.pojo.RefillResult;
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
    public RefillResult getRefillPartStorageInfo(RefillResponse refillResponse) {

        List<PartInfo> resultPartInfoDtoList = getResultPartDtoList(refillResponse);

        return  getRefillResult(refillResponse, resultPartInfoDtoList);
    }

    private RefillResult getRefillResult(RefillResponse refillResponse, List<PartInfo> resultPartInfoDtoList) {

        log.info("Get refillResultDto");

        RefillResult refillResultDto = new RefillResult();

        Set<String> currentPartStorageNameSet = refillResponse.getCurrentPartStorageInfo().getPartStorageNameSet();
        Set<String> targetPartStorageNameSet = refillResponse.getTargetPartStorageInfo().getPartStorageNameSet();

        refillResultDto.setResultPartInfoList(resultPartInfoDtoList);
        refillResultDto.setCurrentPartStorageName(currentPartStorageNameSet);
        refillResultDto.setTargetPartStorageName(targetPartStorageNameSet);

        return refillResultDto;
    }

    private List<PartInfo> getResultPartDtoList(RefillResponse refillResponse) {

        log.info("try to compare target and current list and get result list.");

        List<PartInfo> resultPartInfoDtoList = new ArrayList<>();
        refillResponse
                .getCurrentPartStorageInfo()
                .getPartList()
                .parallelStream().forEach(currentPart -> iterateByTargetPartDtoListAndSetToResultPartDtoList(refillResponse, resultPartInfoDtoList, currentPart));

        return resultPartInfoDtoList;
    }

    private void iterateByTargetPartDtoListAndSetToResultPartDtoList(RefillResponse refillResponse,
                                                                     List<PartInfo> resultPartInfoDtoList,
                                                                     PartInfo currentPartInfo) {

        int matchCount = 0;

        for(PartInfo targetPartInfo : refillResponse.getTargetPartStorageInfo().getPartList()){

            if (currentPartInfo.getPart().getCode().equals(targetPartInfo.getPart().getCode())){

                matchCount++;

            }
        }

        if(matchCount == 0){

            resultPartInfoDtoList.add(currentPartInfo);

        }
    }
}
