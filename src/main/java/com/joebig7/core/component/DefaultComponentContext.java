package com.joebig7.core.component;

import com.joebig7.core.data.HeaderData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/7/19 14:21:24
 */
public class DefaultComponentContext {

    public static final String DEFAULT_SHEET_NAME = "sheet1";

    private Workbook workbook;
    private List<HeaderData> headerDataList;
    private List<Object> contentDataList;

    public DefaultComponentContext(Workbook workbook, List<Object> contentDataList) {
        this(workbook, null, contentDataList);
    }

    public DefaultComponentContext(Workbook workbook, List<HeaderData> headerDataList, List<Object> contentDataList) {
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
        GenericSheetComponent sheetComponent = new GenericSheetComponent(DEFAULT_SHEET_NAME);
        Sheet sheet = sheetComponent.create(workbook);
        return sheet;
    }

    protected List<Row> createRows(Sheet sheet, List<HeaderData> headerDataList, List<Object> contentDataList) {
        GenericRowComponent rowComponent = new GenericRowComponent(headerDataList, contentDataList);
        return rowComponent.create(sheet);
    }

    private void createCells(List<Row> rowList, List<HeaderData> headerDataList, List<Object> contentDataList) {
        GenericCellComponent cellComponent = new GenericCellComponent(headerDataList, contentDataList);
        cellComponent.create(rowList);
    }
}
