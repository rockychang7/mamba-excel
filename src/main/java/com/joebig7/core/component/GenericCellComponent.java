package com.joebig7.core.component;

import com.joebig7.core.data.HeaderData;
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

    @Override
    public Void create(List<Row> rows) {
        if (Objects.isNull(headerDataList) || headerDataList.isEmpty()) {
            writeWithOutHeader(rows, contentDataList);
        } else {
            writeWithHeader(rows, contentDataList);
        }
        return null;
    }

    private void writeWithOutHeader(List<Row> rows, List<List<Object>> contentDataList) {
        for (int i = 0; i < rows.size(); i++) {
            List<Object> rowDataList = contentDataList.get(i);
            for (int j = 0; j < contentDataList.size(); j++) {
                Cell cell = rows.get(i).createCell(i, CellType.STRING);
                cell.setCellValue(String.valueOf(rowDataList.get(j)));
            }
        }
    }

    private void writeWithHeader(List<Row> rows, List<List<Object>> contentDataList) {
        for (int i = 0; i < rows.size(); i++) {
            System.out.println(i);
            if (i == 0) {
                createHeaderRow(rows);
            } else {
                createContentRow(i, rows, contentDataList);
            }
        }
    }

    private void createHeaderRow(List<Row> rows) {
        Row headerRow = rows.get(0);
        for (int i = 0; i < headerDataList.size(); i++) {
            headerRow.createCell(i).setCellValue(headerDataList.get(i).getFieldName());
        }
    }

    public void createContentRow(int index, List<Row> rows, List<List<Object>> contentDataList) {
        List<Object> rowDataList = contentDataList.get(index-1);

        for (int j = 0; j < rowDataList.size(); j++) {
            Cell cell = rows.get(index).createCell(j, headerDataList.get(j).getFieldTypeEnum().getCellType());
            int type = headerDataList.get(j).getFieldTypeEnum().getType();
            String strValue = rowDataList.get(j) + "";
            switch (type) {
                case 0:
                    cell.setCellValue(Integer.parseInt(strValue));
                    break;
                case 1:
                    cell.setCellValue(Long.parseLong(strValue));
                    break;
                case 2:
                    cell.setCellValue(Double.parseDouble(strValue));
                    break;
                case 3:
                    cell.setCellValue(Float.parseFloat(strValue));
                    break;
                case 4:
                    cell.setCellValue(Boolean.parseBoolean(strValue));
                    break;
                case 5:
                case 6:
                    cell.setCellValue(strValue);
                    break;
                case 7:
                    cell.setCellValue("");
                    break;
                default:
                    throw new ExcelManipulationException("no right type for cell value");
            }
        }
    }
}
