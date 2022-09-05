package com.truckplast.analyzer.service.impl;

import com.truckplast.analyzer.dto.PartStorageDto;
import com.truckplast.analyzer.entity.part.PartStorage;
import com.truckplast.analyzer.mapper.PartStorageMapper;
import com.truckplast.analyzer.repository.PartStorageRepository;
import com.truckplast.analyzer.service.PartStorageService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class PartStorageServiceImpl implements PartStorageService {

   private final PartStorageRepository partStorageRepository;

   private final PartStorageMapper partStorageMapper;


    @Override
    public List<PartStorageDto> getPartStorageDtoList() {

        log.info("Try get all partStorages from db");

        List<PartStorage> partStorages = partStorageRepository.getAll();

        return partStorageMapper.mapPartStorageDtoListFromPartStorageList(partStorages);
    }
}
