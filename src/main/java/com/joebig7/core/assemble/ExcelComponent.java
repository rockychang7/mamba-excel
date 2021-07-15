package com.joebig7.core.assemble;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public interface ExcelComponent {

    Sheet createSheet();

    Row createRow();

    Cell createCell();
}
