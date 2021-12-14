package com.joebig7.core;

import com.joebig7.constant.ExcelConstant;
import com.joebig7.core.data.HeaderData;
import com.joebig7.enums.FieldTypeEnum;
import com.joebig7.enums.FileTypeEnum;
import com.joebig7.exception.ExcelManipulationException;
import com.joebig7.exception.ExcelReadException;
import com.joebig7.utils.ExcelUtils;
import com.mamba.core.clazz.ClassUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Objects;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:17:12
 */
public class GenericReader<T> extends AbstractReader<T> {
    public GenericReader(String path, Class type) {
        this(path, type, FileTypeEnum.XLSX, ExcelConstant.DEFAULT_SHEET_NAME);
    }

    public GenericReader(String path, Class type, FileTypeEnum fileTypeEnum) {
        this(path, type, fileTypeEnum, ExcelConstant.DEFAULT_SHEET_NAME);
    }

    public GenericReader(String path, Class type, FileTypeEnum fileTypeEnum, String sheetName) {
        super(path, type, fileTypeEnum, sheetName);
    }

    /**
     * 读取excel内容
     *
     * @param workbook
     */
    @Override
    protected void doInternalRead(Workbook workbook) throws NoSuchFieldException {
        Sheet sheet = workbook.getSheet(sheetName);
        if (Objects.isNull(sheet)) {
            throw new ExcelReadException(String.format("excel sheetName %s is not found", sheetName));
        }
        doDetailRead(sheet);
        readListener.doAfterRead();
        ExcelUtils.closeWorkbook(workbook);
    }


    /**
     * 读取excel具体的内容
     *
     * @param sheet
     * @return
     */
    private void doDetailRead(Sheet sheet) throws NoSuchFieldException {
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            Object cellObj = null;
            Row row = sheet.getRow(i);
            try {
                cellObj = type.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < headerDataList.size(); j++) {
                Cell cell = row.getCell(j);
                //监听事件，全局上下文存储data
                invokeDataAssembly(cellObj, cell, headerDataList.get(j));
            }
            readListener.invoke((T) cellObj);
        }
    }


    /**
     * 组装cell数据
     *
     * @param obj
     * @param cell
     */
    private void invokeDataAssembly(Object obj, Cell cell, HeaderData headerData) throws NoSuchFieldException {
        String fieldName = headerData.getFieldName();
        Object cellValue = getCellValue(cell, headerData.getFieldTypeEnum());
        ClassUtils.setField(obj, type, fieldName, cellValue);
    }

    private Object getCellValue(Cell cell, FieldTypeEnum fieldTypeEnum) {
        switch (fieldTypeEnum) {
            case INTEGER:
                return (int) cell.getNumericCellValue();
            case LONG:
                return (long) cell.getNumericCellValue();
            case DOUBLE:
                return cell.getNumericCellValue();
            case FLOAT:
                return (float) cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case STRING:
            case FORMULA:
                return cell.getStringCellValue();
            case BLANK:
                return "";
            default:
                throw new ExcelManipulationException("no suitable type for cell value");
        }
    }
}
