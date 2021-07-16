package com.joebig7.core.data;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Author JoeBig7
 * @date 2021/7/16 17:22:10
 */
public class GenericExcelComponent implements ExcelComponent {

    public Sheet createSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("sheetName");
        return sheet;
    }


    public Row createRow(Sheet sheet) {
        Row row = sheet.createRow(0);

        return row;
    }
}
