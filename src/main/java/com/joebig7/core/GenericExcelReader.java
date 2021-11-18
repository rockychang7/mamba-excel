package com.joebig7.core;

import com.joebig7.enums.FileTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:17:12
 */
public class GenericExcelReader<T> extends AbstractExcelReader<T> {
    public GenericExcelReader(String path) {
        this(path, FileTypeEnum.XLSX);
    }

    public GenericExcelReader(String path, FileTypeEnum fileTypeEnum) {
        super(path, fileTypeEnum);
        this.path = path;
    }

    /**
     * 读取excel内容
     *
     * @param workbook
     */
    @Override
    protected List<T> doInternalRead(Workbook workbook) {
        workbook.getSheet("");
        return null;
    }
}
