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
 */
public class GenericCellComponent implements ExcelComponent<Void, List<Row>> {

    private List<HeaderData> headerDataList;
    private List<Object> contentDataList;

    public GenericCellComponent(List<HeaderData> headerDataList, List<Object> contentDataList) {
        this.headerDataList = headerDataList;
        this.contentDataList = contentDataList;
    }

    @Override
    public Void create(List<Row> rows) {
        if (Objects.isNull(headerDataList) || headerDataList.isEmpty()) {
            writeWithOutHeader(rows, contentDataList);
        } else {
            writeWithHeader(rows, headerDataList, contentDataList);
        }
        return null;
    }

    private void writeWithOutHeader(List<Row> rows, List<Object> contentDataList) {
        for (Row row : rows) {
            for (int i = 0; i < contentDataList.size(); i++) {
                Cell cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(String.valueOf(contentDataList.get(i)));
            }
        }
    }


    private void writeWithHeader(List<Row> rows, List<HeaderData> headerDataList, List<Object> contentDataList) {
        Row headerRow = rows.get(0);
        //settle title
        for (int i = 0; i < headerDataList.size(); i++) {
            headerRow.createCell(i).setCellValue(headerDataList.get(i).getFieldName());
        }

        //settle content
        for (int i = 1; i < rows.size(); i++) {
            for (int j = 0; j < contentDataList.size(); j++) {
                Cell cell = rows.get(i).createCell(i, headerDataList.get(i).getFieldTypeEnum().getCellType());
                int type = headerDataList.get(i).getFieldTypeEnum().getType();
                String strValue = contentDataList.get(j) + "";
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


}
