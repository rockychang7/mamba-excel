package com.joebig7.core;

import com.joebig7.core.component.ExcelProperty;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.factory.ExcelFactory;
import com.joebig7.core.listener.ReadListener;
import com.joebig7.enums.FileTypeEnum;
import com.joebig7.exception.ExcelHeaderException;
import com.joebig7.exception.ExcelReadException;
import com.joebig7.utils.CommonFileUtils;
import com.mamba.core.collection.CollectionsUtils;
import com.mamba.core.file.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:06:53
 */
public abstract class AbstractReader<T> extends ExcelProperty {
    ReadListener<T> readListener;
    Class type;

    public AbstractReader(String path, Class type, String sheetName) {
        this.path = path;
        this.sheetName = sheetName;
        this.type = type;
    }

    public void read(List<HeaderData> headerDataList, ReadListener<T> readListener) {
        initExcelReadContext(headerDataList, readListener);
        String suffix = FileUtils.suffix(path);
        if (FileTypeEnum.CSV.getFileType().equalsIgnoreCase(suffix)) {
            doCsvWrite();
        } else {
            doExcelRead();
        }
    }

    abstract protected void doExcelRead();

    abstract protected void doCsvWrite();


    /**
     * 初始化读取excels上下文
     *
     * @param headerDataList
     * @param readListener
     */
    private void initExcelReadContext(List<HeaderData> headerDataList, ReadListener<T> readListener) {
        this.headerDataList = headerDataList;
        this.readListener = readListener;
    }

}
