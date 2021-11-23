package com.joebig7.core;

import com.joebig7.constant.ExcelConstant;
import com.joebig7.enums.FileTypeEnum;
import com.joebig7.exception.ExcelReadException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Objects;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:17:12
 */
public class GenericExcelReader<T> extends AbstractExcelReader<T> {
    public GenericExcelReader(String path, Class type) {
        this(path, type, FileTypeEnum.XLSX, ExcelConstant.DEFAULT_SHEET_NAME);
    }

    public GenericExcelReader(String path, Class type, FileTypeEnum fileTypeEnum) {
        this(path, type, fileTypeEnum, ExcelConstant.DEFAULT_SHEET_NAME);
    }

    public GenericExcelReader(String path, Class type, FileTypeEnum fileTypeEnum, String sheetName) {
        super(path, type, fileTypeEnum, sheetName);
    }

    /**
     * 读取excel内容
     *
     * @param workbook
     */
    @Override
    protected void doInternalRead(Workbook workbook) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (Objects.isNull(sheet)) {
            throw new ExcelReadException(String.format("excel sheetName %s is not found", sheetName));
        }
        doDetailRead(sheet);
    }


    /**
     * 读取excel具体的内容
     *
     * @param sheet
     * @return
     */
    private void doDetailRead(Sheet sheet) {
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < headerDataList.size(); j++) {
                //监听事件，全局上下文来存储 data
                Cell cell = row.getCell(j);

            }

        }
    }
}
