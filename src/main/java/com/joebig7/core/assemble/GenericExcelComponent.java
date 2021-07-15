package com.joebig7.core.assemble;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Author JoeBig7
 * @date 2021/7/15 17:58:30
 */
public class GenericExcelComponent implements ExcelComponent {
    private Workbook workbook;

    public GenericExcelComponent(Workbook workbook) {
        this.workbook = workbook;
    }


    @Override
    public Sheet createSheet() {
        return null;
    }

    @Override
    public Row createRow() {
        return null;
    }

    @Override
    public Cell createCell() {
        return null;
    }
}
