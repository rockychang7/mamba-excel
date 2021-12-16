package com.joebig7.core.component;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Author JoeBig7
 * @date 2021/7/16 17:22:10
 * @description common sheet component
 */
public class GenericSheetComponent implements ExcelComponent<Sheet, Workbook> {

    public static final String DEFAULT_SHEET = "default";
    private String name;

    public GenericSheetComponent(String name) {
        this.name = name == null ? DEFAULT_SHEET : name;
    }

    /**
     * 创建sheet
     *
     * @param workbook
     * @return
     */
    @Override
    public Sheet create(Workbook workbook) {
        return workbook.createSheet(name);
    }
}
