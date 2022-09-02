package com.truckplast.analyzer.service.impl;


import com.truckplast.analyzer.constant.PartStorageConstant;
import com.truckplast.analyzer.dto.PartStorageInfoDto;
import com.truckplast.analyzer.dto.RefillRequestDto;
import com.truckplast.analyzer.dto.RefillResponseDto;
import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.exeption_handler.exception.WrongPartStorageNameException;
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
    public RefillResponseDto getRefilledInfo(RefillRequestDto refillRequestDto) {

        log.info("Create RefillResponseDto by part storage names ");

        refillRequestDto.getCurrentPartStorageNameSet().forEach(this::checkPartStorageName);
        refillRequestDto.getTargetPartStorageNameSet().forEach(this::checkPartStorageName);

        List<PartInfo> targetPartInfoStorageList = partInfoRepository.getAllByPartStorageName(refillRequestDto.getTargetPartStorageNameSet());
        List<PartInfo> currentPartInfoStorageList = partInfoRepository.getAllByPartStorageName(refillRequestDto.getCurrentPartStorageNameSet());

        PartStorageInfoDto targetPartStorageInfoDto = getPartStorageInfoDto(refillRequestDto.getTargetPartStorageNameSet(), targetPartInfoStorageList);
        PartStorageInfoDto currentPartStorageInfoDto = getPartStorageInfoDto(refillRequestDto.getCurrentPartStorageNameSet(), currentPartInfoStorageList);

        return new RefillResponseDto(targetPartStorageInfoDto, currentPartStorageInfoDto);
    }

    private PartStorageInfoDto getPartStorageInfoDto(Set<String> partStorageName, List<PartInfo> targetPartStorageList) {

        PartStorageInfoDto partStorageInfoDto = new PartStorageInfoDto();

        partStorageInfoDto.setPartStorageNameSet(partStorageName);
        partStorageInfoDto.setPartList(targetPartStorageList);

        return partStorageInfoDto;
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
