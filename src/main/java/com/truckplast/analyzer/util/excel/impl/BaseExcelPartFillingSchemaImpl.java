package com.truckplast.analyzer.util.excel.impl;

import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.util.excel.ExcelPartFillingSchema;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BaseExcelPartFillingSchemaImpl implements ExcelPartFillingSchema {

    @Override
    public void basePartFileFillingSchema(PartInfo partInfo, XSSFSheet sheet, int rowCounter) {

        Row row = sheet.createRow(rowCounter);

        for (int i = 0; i < 4; i++) {

            Cell cell = row.createCell(i);

            switch (i) {

                case 0:
                    cell.setCellValue(partInfo.getPart().getCode());
                    break;
                case 1:
                    cell.setCellValue(partInfo.getPart().getBrand().getName());
                    break;
                case 2:
                    cell.setCellValue(partInfo.getPart().getDescription());
                    break;
                case 3:
                    cell.setCellValue(partInfo.getCount());
                    break;

            }
        }
    }

    @Override
    public void setFirstRow(XSSFWorkbook workbook, XSSFSheet sheet) {

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = (workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);

        headerStyle.setFont(font);
        Row header = sheet.createRow(0);

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

    }
}
