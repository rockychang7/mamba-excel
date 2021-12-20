package com.joebig7.core.component;

import com.joebig7.core.data.HeaderData;
import com.joebig7.enums.FieldTypeEnum;
import com.joebig7.exception.ExcelManipulationException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Objects;

/**
 * @Author JoeBig7
 * @date 2021/7/19 16:49:37
 * @description common cell component
 */
public class GenericCellComponent implements ExcelComponent<Void, List<Row>> {

    private List<HeaderData> headerDataList;
    private List<List<Object>> contentDataList;

    public GenericCellComponent(List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        this.headerDataList = headerDataList;
        this.contentDataList = contentDataList;
    }

    /**
     * 创建cell
     *
     * @param rows
     * @return
     */
    @Override
    public Void create(List<Row> rows) {
        if (Objects.isNull(headerDataList) || headerDataList.isEmpty()) {
            createWithOutHeader(rows, contentDataList);
        } else {
            createWithHeader(rows, contentDataList);
        }
        return null;
    }

    /**
     * 创建不包含标题头的cell
     *
     * @param rows
     * @param contentDataList
     */
    private void createWithOutHeader(List<Row> rows, List<List<Object>> contentDataList) {
        for (int i = 0; i < rows.size(); i++) {
            List<Object> rowDataList = contentDataList.get(i);
            for (int j = 0; j < rowDataList.size(); j++) {
                Cell cell = rows.get(i).createCell(j, CellType.STRING);
                cell.setCellValue(String.valueOf(rowDataList.get(j)));
            }
        }
    }

    /**
     * 创建包含标题头的cell
     *
     * @param rows
     * @param contentDataList
     */
    private void createWithHeader(List<Row> rows, List<List<Object>> contentDataList) {
        for (int i = 0; i < rows.size(); i++) {
            if (i == 0) {
                createHeaderCell(rows);
            } else {
                createContentCell(i, rows, contentDataList);
            }
        }
    }

    /**
     * 创建header中cell具体操作
     *
     * @param rows
     */
    private void createHeaderCell(List<Row> rows) {
        Row headerRow = rows.get(0);
        for (int i = 0; i < headerDataList.size(); i++) {
            headerRow.createCell(i).setCellValue(headerDataList.get(i).getFieldName());
        }
    }

    /**
     * 创建content中cell具体操作
     *
     * @param rows
     */
    public void createContentCell(int index, List<Row> rows, List<List<Object>> contentDataList) {
        List<Object> rowDataList = contentDataList.get(index - 1);
        for (int j = 0; j < rowDataList.size(); j++) {
            FieldTypeEnum type = headerDataList.get(j).getFieldTypeEnum();
            Cell cell = rows.get(index).createCell(j, type.getCellType());
            String strValue = rowDataList.get(j) + "";
            switch (type) {
                case INTEGER:
                    cell.setCellValue(Integer.parseInt(strValue));
                    break;
                case LONG:
                    cell.setCellValue(Long.parseLong(strValue));
                    break;
                case DOUBLE:
                    cell.setCellValue(Double.parseDouble(strValue));
                    break;
                case FLOAT:
                    cell.setCellValue(Float.parseFloat(strValue));
                    break;
                case BOOLEAN:
                    cell.setCellValue(Boolean.parseBoolean(strValue));
                    break;
                case STRING:
                case FORMULA:
                    cell.setCellValue(strValue);
                    break;
                case BLANK:
                    cell.setCellValue("");
                    break;
                default:
                    throw new ExcelManipulationException("no suitable type for cell value");
            }
        }
    }
}
