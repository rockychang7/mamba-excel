package com.joebig7.core;

import com.joebig7.constant.ExcelConstant;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.factory.CsvFactory;
import com.joebig7.core.factory.ExcelFactory;
import com.joebig7.enums.FieldTypeEnum;
import com.joebig7.exception.ExcelManipulationException;
import com.joebig7.exception.ExcelReadException;
import com.joebig7.utils.CommonFileUtils;
import com.joebig7.utils.CsvUtils;
import com.joebig7.utils.ExcelUtils;
import com.mamba.core.clazz.ClassUtils;
import com.mamba.core.collection.CollectionsUtils;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:17:12
 */
public class GenericReader<T> extends AbstractReader<T> {
    public GenericReader(String path, Class type) {
        this(path, type, ExcelConstant.DEFAULT_SHEET_NAME);
    }

    public GenericReader(String path, Class type, String sheetName) {
        super(path, type, sheetName);
    }

    /**
     * 读取excel内容
     */
    @Override
    protected void doExcelRead() {
        if (CollectionsUtils.isEmpty(headerDataList)) {
            throw new ExcelReadException("excel header can not be null");
        }
        FileInputStream fileInputStream = CommonFileUtils.getFileInputStream(path);
        Workbook workbook = ExcelFactory.readInstance(fileInputStream, path);

        if (Objects.isNull(workbook)) {
            throw new ExcelReadException(String.format("excel workbook %s is not found", sheetName));
        }

        Sheet sheet = workbook.getSheet(sheetName);
        if (Objects.isNull(sheet)) {
            throw new ExcelReadException(String.format("excel sheetName %s is not found", sheetName));
        }
        try {
            doSpecificExcelRead(sheet);
            readListener.doAfterRead();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            ExcelUtils.closeWorkbook(workbook);
            CommonFileUtils.closeInputStream(fileInputStream);
        }
    }


    /**
     * 读取csv文件内容
     */
    @Override
    protected void doCsvRead() {
        CSVParser parser = null;
        Reader reader = CommonFileUtils.getFileReader(path);
        try {
            String[] headers = CollectionsUtils.toStringArray(headerDataList.stream().map(HeaderData::getFieldName).collect(Collectors.toList()));
            parser = CsvFactory.readInstance(reader, headers);
            doSpecificCsvRead(parser.getRecords());
            readListener.doAfterRead();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CsvUtils.close(parser);
            CommonFileUtils.closeReader(reader);
        }
    }

    /**
     * 读取excel具体操作
     *
     * @param sheet
     * @return
     */
    private void doSpecificExcelRead(Sheet sheet) throws NoSuchFieldException {
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            Object cellObj = obtainTargetObj();
            Row row = sheet.getRow(i);
            if(Objects.isNull(row)){
                continue;
            }

            for (int j = 0; j < headerDataList.size(); j++) {
                Cell cell = row.getCell(j);
                if(Objects.isNull(cell)){
                    continue;
                }
                //监听事件，全局上下文存储data
                HeaderData headerData = headerDataList.get(j);
                String fieldName = headerData.getFieldName();
                Object cellValue = getCellValue(cell, headerData.getFieldTypeEnum());
                ClassUtils.setField(cellObj, type, fieldName, cellValue);
            }
            readListener.invoke((T) cellObj);
        }
    }


    /**
     * @param recordList
     * @throws NoSuchFieldException
     */
    private void doSpecificCsvRead(List<CSVRecord> recordList) throws NoSuchFieldException {
        for (int i = 1; i < recordList.size(); i++) {
            CSVRecord record = recordList.get(i);
            Object csvObj = obtainTargetObj();
            for (int j = 0; j < headerDataList.size(); j++) {
                HeaderData headerData = headerDataList.get(j);
                String value = record.get(headerData.getFieldName());
                String fieldName = headerData.getFieldName();
                Object objVal = getCsvValue(value, headerData.getFieldTypeEnum());
                ClassUtils.setField(csvObj, type, fieldName, objVal);
            }
            readListener.invoke((T) csvObj);
        }
    }

    /**
     * 获取结果对象
     *
     * @return
     */
    private Object obtainTargetObj() {
        Object obj = null;
        try {
            obj = type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * 解析excel值得类型
     *
     * @param cell
     * @param fieldTypeEnum
     * @return
     */
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
                cell.setCellType(CellType.STRING);
                return cell.getStringCellValue();
            case BLANK:
                return "";
            case BIG_DECIMAL:
                return new BigDecimal(cell.getNumericCellValue());
            default:
                throw new ExcelManipulationException("no suitable type for cell value");
        }
    }


    /**
     * 解析csv值得类型
     *
     * @param value
     * @param fieldTypeEnum
     * @return
     */
    private Object getCsvValue(String value, FieldTypeEnum fieldTypeEnum) {
        switch (fieldTypeEnum) {
            case INTEGER:
                return Integer.parseInt(value);
            case LONG:
                return Long.parseLong(value);
            case DOUBLE:
                return Double.parseDouble(value);
            case FLOAT:
                return Float.parseFloat(value);
            case BOOLEAN:
                return Boolean.parseBoolean(value);
            case STRING:
            case FORMULA:
                return value;
            case BLANK:
                return "";
            default:
                throw new ExcelManipulationException("no suitable type for csv value");
        }
    }
}
