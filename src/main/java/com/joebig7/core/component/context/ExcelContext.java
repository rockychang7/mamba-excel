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
 * @descripition excel组件上下文
 */
public class ExcelContext {
    private List<HeaderData> headerDataList;
    private Workbook workbook;

    private List<List<Object>> contentDataList;

    public ExcelContext(Workbook workbook, List<List<Object>> contentDataList) {
        this(workbook, null, contentDataList);
    }

    public ExcelContext(Workbook workbook, List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        this.workbook = workbook;
        this.headerDataList = headerDataList;
        this.contentDataList = contentDataList;
    }

    /**
     * 组装组件
     *
     * @return
     */
    public Workbook combine() {
        Sheet sheet = createSheet();
        List<Row> rowList = createRows(sheet, headerDataList, contentDataList);
        createCells(rowList, headerDataList, contentDataList);
        return workbook;
    }

    /**
     * 创建sheet组件
     *
     * @return
     */
    protected Sheet createSheet() {
        GenericSheetComponent sheetComponent = new GenericSheetComponent(ExcelConstant.DEFAULT_SHEET_NAME);
        Sheet sheet = sheetComponent.create(workbook);
        return sheet;
    }

    /**
     * 创建row组件
     *
     * @param sheet
     * @param headerDataList
     * @param contentDataList
     * @return
     */
    protected List<Row> createRows(Sheet sheet, List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        GenericRowComponent rowComponent = new GenericRowComponent(headerDataList, contentDataList);
        return rowComponent.create(sheet);
    }

    /**
     * 创建cell组件
     *
     * @param rowList
     * @param headerDataList
     * @param contentDataList
     */
    private void createCells(List<Row> rowList, List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        GenericCellComponent cellComponent = new GenericCellComponent(headerDataList, contentDataList);
        cellComponent.create(rowList);
    }
}
