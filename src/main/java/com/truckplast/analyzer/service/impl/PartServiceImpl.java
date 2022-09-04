package com.truckplast.analyzer.service.impl;


import com.truckplast.analyzer.constant.PartStorageConstant;
import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.exeption_handler.exception.WrongPartStorageNameException;
import com.truckplast.analyzer.pojo.PartStorageInfo;
import com.truckplast.analyzer.pojo.RefillRequest;
import com.truckplast.analyzer.pojo.RefillResponse;
import com.truckplast.analyzer.repository.PartInfoRepository;
import com.truckplast.analyzer.service.PartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Slf4j
@Data
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartInfoRepository partInfoRepository;


    @Override
    public RefillResponse getRefilledInfo(RefillRequest refillRequestDto) {

        log.info("Create RefillResponseDto by part storage names ");

        refillRequestDto.getCurrentPartStorageNameSet().forEach(this::checkPartStorageName);
        refillRequestDto.getTargetPartStorageNameSet().forEach(this::checkPartStorageName);

        List<PartInfo> targetPartInfoStorageList = partInfoRepository.getAllByPartStorageName(refillRequestDto.getTargetPartStorageNameSet());
        List<PartInfo> currentPartInfoStorageList = partInfoRepository.getAllByPartStorageName(refillRequestDto.getCurrentPartStorageNameSet());

        PartStorageInfo targetPartStorageInfo = getPartStorageInfoDto(refillRequestDto.getTargetPartStorageNameSet(), targetPartInfoStorageList);
        PartStorageInfo currentPartStorageInfo = getPartStorageInfoDto(refillRequestDto.getCurrentPartStorageNameSet(), currentPartInfoStorageList);

        return new RefillResponse(targetPartStorageInfo, currentPartStorageInfo);
    }

    private PartStorageInfo getPartStorageInfoDto(Set<String> partStorageName, List<PartInfo> targetPartStorageList) {

        PartStorageInfo partStorageInfo = new PartStorageInfo();

        partStorageInfo.setPartStorageNameSet(partStorageName);
        partStorageInfo.setPartList(targetPartStorageList);

        return partStorageInfo;
    }

    private void checkPartStorageName(String partStorageName) {

        if (partStorageName != null) {

            if (!PartStorageConstant.PART_STORAGE_NAME_SET.contains(partStorageName)) {

                throw new WrongPartStorageNameException("Wrong part storage name " + partStorageName);

            }

        } else {

            throw new WrongPartStorageNameException("Part storage name is null");
        }
    }
}
