package com.truckplast.analyzer.service.file.impl;


import com.truckplast.analyzer.dto.FileInfoDto;
import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.pojo.RefillResult;
import com.truckplast.analyzer.service.file.FileCreatorService;
import com.truckplast.analyzer.util.excel.impl.BaseExcelPartFillingSchemaImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class ExcelFileCreatorServiceImpl implements FileCreatorService {

    private final BaseExcelPartFillingSchemaImpl schemaStorage;

    @Override
    public FileInfoDto getFile(RefillResult refillResult) {

        XSSFWorkbook workbook = getWorkBook(refillResult);

        FileInfoDto fileInfoDto = new FileInfoDto();
        fileInfoDto.setExtension(".xls");
        fileInfoDto.setFileName("result");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        tryToWriteWorkBook(workbook, baos);
        fileInfoDto.setFileBytes(baos.toByteArray());


        return fileInfoDto;
    }

    @SneakyThrows
    private XSSFWorkbook getWorkBook(RefillResult refillResult) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("refill result");

        schemaStorage.setFirstRow(workbook, sheet);

        List<PartInfo> partInfoList = refillResult.getResultPartInfoList();
        int rowCounter = 1;

        for (PartInfo partInfo : partInfoList) {

            schemaStorage.basePartFileFillingSchema(partInfo, sheet, rowCounter);
            rowCounter++;

        }

        return workbook;
    }


    private void tryToWriteWorkBook(XSSFWorkbook workbook, ByteArrayOutputStream baos) {

        try {

            workbook.write(baos);

        } catch (IOException exception) {

            exception.printStackTrace();
        }
    }
}
