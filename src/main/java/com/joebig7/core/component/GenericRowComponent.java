package com.joebig7.core.component;

import com.joebig7.core.data.HeaderData;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Objects;

/**
 * @Author JoeBig7
 * @date 2021/7/19 14:17:44
 * @description common row component
 */
public class GenericRowComponent implements ExcelComponent<List<Row>, Sheet> {

    private List<HeaderData> headerDataList;
    private List<List<Object>> contentDataList;


    public GenericRowComponent(List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        this.headerDataList = headerDataList;
        this.contentDataList = contentDataList;
    }

    /**
     * 创建row
     * @param sheet
     * @return
     */
    @Override
    public List<Row> create(Sheet sheet) {
        int rowLen = 0;
        List<Row> rowList = Lists.newArrayList();

        if (!Objects.isNull(headerDataList) && !headerDataList.isEmpty()) {
            rowLen++;
        }

        if (!Objects.isNull(contentDataList) && !contentDataList.isEmpty()) {
            rowLen += contentDataList.size();
        }

        for (int i = 0; i < rowLen; i++) {
            Row row = sheet.createRow(i);
            rowList.add(row);
        }

        return rowList;
    }
}
