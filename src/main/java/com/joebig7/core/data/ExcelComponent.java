package com.joebig7.core.data;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelComponent {

    Sheet createSheet(Workbook workbook);


    Row createRow(Sheet sheet);


}
