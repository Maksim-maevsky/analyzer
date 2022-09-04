package com.truckplast.analyzer.service.file.impl;


import com.truckplast.analyzer.constant.ParserConstant;
import com.truckplast.analyzer.constant.PartStorageConstant;
import com.truckplast.analyzer.entity.FileInfo;
import com.truckplast.analyzer.entity.part.Brand;
import com.truckplast.analyzer.entity.part.Part;
import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.entity.part.PartStorage;
import com.truckplast.analyzer.exeption_handler.exception.EmptyFileNotFoundException;
import com.truckplast.analyzer.exeption_handler.exception.WorkBookCreationIOException;
import com.truckplast.analyzer.exeption_handler.exception.WrongPartStorageKeyException;
import com.truckplast.analyzer.repository.*;
import com.truckplast.analyzer.service.file.FileParserService;
import com.truckplast.analyzer.util.FileUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class FileParserServiceImpl implements FileParserService {


    private final PartInfoRepository partInfoRepository;

    private final TransactionPartRepository transactionPartRepository;

    private final FileInfoRepository fileInfoRepository;

    private final PartRepository partRepository;

    private final BrandRepository brandRepository;

    @Value("${file.path}")
    private String filePath;


    @Transactional
    @Override
    public void parsAndSave(List<FileInfo> fileInfoList, UUID mailInfoId) {

        log.info("Try to pars MailInfo");
        fileInfoList.parallelStream().forEach(fileInfo -> {
            parsFileInfos(fileInfo);
            fileInfo.setMailInfoId(mailInfoId);
            fileInfoRepository.save(fileInfo);
        });


    }

    private void parsFileInfos(FileInfo fileInfo) {

        String fileName = getFileName(fileInfo);

        log.info("Try to get File from byte array");
        File file = FileUtil.getFile(filePath, fileName, fileInfo.getFileBytes());

        String storageKey = fileInfo.getFileName();
        PartStorage partStorage = getPartStorage(storageKey);

        List<PartInfo> parts = tryToGetPartInfoList(partStorage, file);
        deletePreviousPartsAndSaveCurrent(parts, partStorage.getId());
        fileInfo.setId(UUID.randomUUID());

    }

    private String getFileName(FileInfo fileInfo) {

        return fileInfo.getFileName() + "." + fileInfo.getExtension();
    }

    private void deletePreviousPartsAndSaveCurrent(List<PartInfo> partInfoList, short partStorageId) {

        partInfoRepository.delete(partStorageId);
        partInfoRepository.saveAll(partInfoList);
        transactionPartRepository.saveAll(partInfoList);

    }

    private List<PartInfo> tryToGetPartInfoList(PartStorage partStorage, File file) {

        List<PartInfo> parts = new ArrayList<>();

        log.info("Parse file rows.");

        try (FileInputStream fileStream = new FileInputStream(file);

             Workbook workbook = WorkbookFactory.create(fileStream)) {

            iterateAllRows(partStorage, parts, workbook);

            return parts;

        } catch (FileNotFoundException e) {

            throw new EmptyFileNotFoundException("Wrong file path.");

        } catch (IOException exception) {

            throw new WorkBookCreationIOException("Exception when Work Book creating.");

        } finally {

            FileUtil.tryDeleteFile(file);

        }
    }

    private void iterateAllRows(PartStorage partStorage, List<PartInfo> parts, Workbook workbook) {

        Sheet firstSheet = workbook.getSheetAt(ParserConstant.FIRST_SHEET);

        Map<String, Brand> brandMap = new HashMap<>();

        for (int currentRow = 0; currentRow < firstSheet.getLastRowNum(); currentRow++) {

            if (isFirstRow(currentRow)) continue;

            Row nextRow = firstSheet.getRow(currentRow);


            PartInfo partInfo = iterateOneRowAndGetPart(brandMap, nextRow);

            if (partInfo != null) {

                setPartStorageIdAndId(partStorage, partInfo);
                parts.add(partInfo);
            }

        }
    }

    private boolean isFirstRow(int currentRow) {

        return currentRow == ParserConstant.FIRST_ROW;

    }

    private void setPartStorageIdAndId(PartStorage partStorage, PartInfo part) {

        part.setPartStorageId(partStorage.getId());
        part.setId(UUID.randomUUID());


    }

    private PartStorage getPartStorage(String storageKey) {

        return Optional.of(PartStorageConstant.PART_STORAGE_MAP.get(storageKey))
                .orElseThrow(() -> new WrongPartStorageKeyException("Wrong part storage key " + storageKey));
    }

    private PartInfo iterateOneRowAndGetPart(Map<String, Brand> brandMap, Row nextRow) {

        PartInfo partInfo = getEmptyPartInfo();

        for (short currentColumn = 0; currentColumn <= 5; currentColumn++) {

            Cell cell = nextRow.getCell(currentColumn);
            getAndSetCellTypeToPart(cell, partInfo, currentColumn);
            partInfo.setCreateDate(LocalDateTime.now());

        }

        return checkBrandAndCompletePartInfo(brandMap, partInfo);
    }

    private PartInfo getEmptyPartInfo() {

        PartInfo partInfo = new PartInfo();
        partInfo.setPart(new Part());
        partInfo.getPart().setBrand(new Brand());

        return partInfo;
    }

    private PartInfo checkBrandAndCompletePartInfo(Map<String, Brand> brandMap, PartInfo partInfo) {

        String brandNameFromFile = partInfo.getPart().getBrand().getName();

        Brand checkedBrand;

        if (brandMap.containsKey(brandNameFromFile)) {

            checkedBrand = brandMap.get(brandNameFromFile);

        } else {

            Optional<Brand> optionalBrand = brandRepository.getByName(brandNameFromFile);

            if (optionalBrand.isPresent()) {

                checkedBrand = optionalBrand.get();
                brandMap.put(checkedBrand.getName(), checkedBrand);

            } else {

                log.error("Brand " + brandNameFromFile + " is no present.");

                return null;
            }
        }

        partInfo.getPart().setBrand(checkedBrand);
        checkPartIdAndSetInfo(partInfo.getPart());

        return partInfo;

    }

    private void checkPartIdAndSetInfo(Part part) {

        Optional<UUID> id = partRepository.getIdByCodeAndBrand(part.getCode(), part.getBrand().getName());

        if (id.isPresent()) {

            part.setId(id.get());

        } else {

            part.setCreateDate(LocalDateTime.now());
            part.setId(UUID.randomUUID());

            partRepository.save(part);

        }
    }

    private void getAndSetCellTypeToPart(Cell cell, PartInfo partInfo, short column) {

        switch (column) {

            case 0:
                partInfo.getPart().setCode(cell.getStringCellValue());
                break;

            case 1:
                partInfo.getPart().getBrand().setName(cell.getStringCellValue());
                break;

            case 2:
                partInfo.getPart().setDescription(cell.getStringCellValue());
                break;

            case 3:

            case 4:
                break;

            case 5:
                partInfo.setCount((int) cell.getNumericCellValue());
                break;

        }
    }
}


