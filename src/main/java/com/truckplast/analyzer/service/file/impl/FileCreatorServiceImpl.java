package com.truckplast.analyzer.service.file.impl;


import com.truckplast.analyzer.dto.FileInfoDto;
import com.truckplast.analyzer.dto.PartInfoDto;
import com.truckplast.analyzer.dto.RefillResultDto;
import com.truckplast.analyzer.service.file.FileCreatorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class FileCreatorServiceImpl implements FileCreatorService {


    @Override
    public FileInfoDto getFile(RefillResultDto refillResultDto) {

        XSSFWorkbook workbook = getWorkBook(refillResultDto);

        FileInfoDto fileInfoDto = new FileInfoDto();
        fileInfoDto.setExtension(".xls");
        fileInfoDto.setFileName("result");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {

            workbook.write(baos);

        } catch (IOException exception) {

            exception.printStackTrace();
        }

        fileInfoDto.setFileBytes(baos.toByteArray());


        return fileInfoDto;
    }

    @SneakyThrows
    private XSSFWorkbook getWorkBook(RefillResultDto refillResultDto) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("task");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Code");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Brand");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Description");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Count");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Transfer count");
        headerCell.setCellStyle(headerStyle);


        List<PartInfoDto> partInfoDtoList = refillResultDto.getResultPartInfoDtoList();

        int rowCounter = 1;

        for (PartInfoDto partInfoDto : partInfoDtoList) {

            Row row = sheet.createRow(rowCounter);

            for (int i = 0; i < 4; i++) {

                Cell cell = row.createCell(i);

                switch (i) {

                    case 0:
                        cell.setCellValue(partInfoDto.getPart().getCode());
                        break;
                    case 1:
                        cell.setCellValue(partInfoDto.getPart().getBrand().getName());
                        break;
                    case 2:
                        cell.setCellValue(partInfoDto.getPart().getDescription());
                        break;
                    case 3:
                        cell.setCellValue(partInfoDto.getCount());
                        break;

                }
            }

            rowCounter++;
        }

        return workbook;
    }
}
