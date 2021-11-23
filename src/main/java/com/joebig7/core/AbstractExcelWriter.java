package com.joebig7.core;

import com.joebig7.core.component.ExcelProperty;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.factory.WorkBookFactory;
import com.joebig7.enums.FileTypeEnum;
import com.joebig7.utils.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:37:40
 * @description excel writer parent class
 */
public abstract class AbstractExcelWriter extends ExcelProperty {
    protected List<List<Object>> contentDataList;

    public AbstractExcelWriter(String path, FileTypeEnum fileTypeEnum) {
        this.path = path;
        this.fileTypeEnum = fileTypeEnum;
    }

    public void write(List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        this.headerDataList = headerDataList;
        this.contentDataList = contentDataList;
        write(path, fileTypeEnum);
    }

    private void write(String path, FileTypeEnum fileTypeEnum) {
        FileOutputStream fis = FileUtils.getFileOutputStream(path);
        doWrite(fis, WorkBookFactory.writeInstance(fileTypeEnum,path));
    }

    abstract protected void doWrite(FileOutputStream fis, Workbook workbook);
}
