package com.joebig7.core;

import com.joebig7.core.component.ExcelProperty;
import com.joebig7.core.data.HeaderData;
import com.joebig7.core.factory.ExcelFactory;
import com.joebig7.enums.FileTypeEnum;
import com.mamba.core.file.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:37:40
 * @description excel writer parent class
 */
public abstract class AbstractWriter extends ExcelProperty {
    protected List<List<Object>> contentDataList;

    public AbstractWriter(String path) {
        this.path = path;
    }

    public void write(List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        this.headerDataList = headerDataList;
        this.contentDataList = contentDataList;
        write(path);
    }

    private void write(String path) {
        String suffix = FileUtils.suffix(path);
        if(FileTypeEnum.CSV.getFileType().equalsIgnoreCase(suffix)){
            doCsvWrite(path);
        }else {
            doExcelWrite(ExcelFactory.writeInstance(path));
        }
    }

    abstract protected void doExcelWrite(Workbook workbook);

    abstract protected void doCsvWrite(String path);
}
