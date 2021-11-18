package com.joebig7.core.component.context;

import com.joebig7.constant.ExcelConstant;
import com.joebig7.core.component.GenericCellComponent;
import com.joebig7.core.component.GenericRowComponent;
import com.joebig7.core.component.GenericSheetComponent;
import com.joebig7.core.data.HeaderData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/7/19 14:21:24
 * @descripition excel data context
 */
public class WriteComponentContext{
    private List<HeaderData> headerDataList;
    private Workbook workbook;

    private List<List<Object>> contentDataList;

    public WriteComponentContext(Workbook workbook, List<List<Object>> contentDataList) {
        this(workbook, null, contentDataList);
    }

    public WriteComponentContext(Workbook workbook, List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        this.workbook = workbook;
        this.headerDataList = headerDataList;
        this.contentDataList = contentDataList;
    }

    public Workbook combine() {
        Sheet sheet = createSheet();
        List<Row> rowList = createRows(sheet, headerDataList, contentDataList);
        createCells(rowList, headerDataList, contentDataList);
        return workbook;
    }

    protected Sheet createSheet() {
        GenericSheetComponent sheetComponent = new GenericSheetComponent(ExcelConstant.DEFAULT_SHEET_NAME);
        Sheet sheet = sheetComponent.create(workbook);
        return sheet;
    }

    protected List<Row> createRows(Sheet sheet, List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        GenericRowComponent rowComponent = new GenericRowComponent(headerDataList, contentDataList);
        return rowComponent.create(sheet);
    }

    private void createCells(List<Row> rowList, List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        GenericCellComponent cellComponent = new GenericCellComponent(headerDataList, contentDataList);
        cellComponent.create(rowList);
    }
}
