package com.truckplast.analyzer.util.excel;

import com.truckplast.analyzer.entity.part.PartInfo;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelPartFillingSchema {

    void basePartFileFillingSchema(PartInfo partInfo, XSSFSheet sheet, int rowCounter);

    void setFirstRow(XSSFWorkbook workbook, XSSFSheet sheet);
}
