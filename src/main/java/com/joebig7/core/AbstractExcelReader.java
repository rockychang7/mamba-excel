package com.joebig7.core;

import com.joebig7.core.component.ExcelProperty;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.factory.WorkBookFactory;
import com.joebig7.core.listener.ReadListener;
import com.joebig7.enums.FileTypeEnum;
import com.joebig7.exception.ExcelHeaderException;
import com.joebig7.exception.ExcelReadException;
import com.joebig7.utils.FileUtils;
import com.mamba.core.collection.CollectionsUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:06:53
 */
public abstract class AbstractExcelReader<T> extends ExcelProperty {
    ReadListener<T> readListener;
    Class type;

    public AbstractExcelReader(String path, Class type, FileTypeEnum fileTypeEnum, String sheetName) {
        this.path = path;
        this.fileTypeEnum = fileTypeEnum;
        this.sheetName = sheetName;
        this.type = type;
    }

    public void read(List<HeaderData> headerDataList, ReadListener<T> readListener) {
        initReadContext(headerDataList, readListener);

        if (CollectionsUtils.isEmpty(headerDataList)) {
            throw new ExcelReadException("excel header can not be null");
        }

        FileInputStream fileInputStream = FileUtils.getFileInputStream(path);
        try {
            doInternalRead(WorkBookFactory.readInstance(fileInputStream, fileTypeEnum, path));
        } catch (NoSuchFieldException e) {
            throw new ExcelHeaderException("field name is not suitable for pointed class");
        }finally {
             FileUtils.closeInputStream(fileInputStream);
        }
    }

    abstract protected void doInternalRead(Workbook workbook) throws NoSuchFieldException;


    /**
     * 初始化读取excels上下文
     *
     * @param headerDataList
     * @param readListener
     */
    private void initReadContext(List<HeaderData> headerDataList, ReadListener<T> readListener) {
        this.headerDataList = headerDataList;
        this.readListener = readListener;
    }

}
