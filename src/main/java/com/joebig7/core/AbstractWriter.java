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
    public AbstractWriter(String path) {
        this.path = path;
    }

    public void write() {
        write(path);
    }

    public void write(List<HeaderData> headerDataList, List<List<Object>> contentDataList) {
        this.headerDataList = headerDataList;
        this.contentDataList = contentDataList;
        write(path);
    }

    private void write(String path) {
        String suffix = FileUtils.suffix(path);
        if (FileTypeEnum.CSV.getFileType().equalsIgnoreCase(suffix)) {
            doCsvWrite(path);
        } else {
            doExcelWrite(ExcelFactory.writeInstance(path));
        }
    }

    /**
     * 写excel文件 可以覆盖定性定制化操作
     *
     * @param workbook
     */
    abstract protected void doExcelWrite(Workbook workbook);

    /**
     * 写csv文件  可以覆盖进行定制化操作
     *
     * @param path
     */
    abstract protected void doCsvWrite(String path);
}
