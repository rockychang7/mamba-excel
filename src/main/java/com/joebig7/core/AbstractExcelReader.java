package com.joebig7.core;

import com.joebig7.core.component.ExcelProperty;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.factory.WorkBookFactory;
import com.joebig7.enums.FileTypeEnum;
import com.joebig7.utils.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:06:53
 */
public abstract class AbstractExcelReader<T> extends ExcelProperty {
    public AbstractExcelReader(String path, FileTypeEnum fileTypeEnum) {
        this.path = path;
        this.fileTypeEnum = fileTypeEnum;
    }

    public List<T> read(List<HeaderData> headerDataList) {
        this.headerDataList = headerDataList;
        FileInputStream fileInputStream = FileUtils.getFileInputStream(path);
        return doInternalRead(WorkBookFactory.readInstance(fileInputStream, fileTypeEnum));
    }

    abstract protected List<T> doInternalRead(Workbook workbook);
}
