package com.joebig7.core;

import com.joebig7.core.component.context.WriteComponentContext;
import com.joebig7.enums.FileTypeEnum;
import com.joebig7.utils.ExcelUtils;
import com.joebig7.utils.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author JoeBig7
 * @date 2021/7/16 16:39:56
 * @description generic excel writer class
 */
public class GenericExcelWriter extends AbstractExcelWriter {
    public GenericExcelWriter(String path) {
        this(path, FileTypeEnum.XLSX);
    }

    public GenericExcelWriter(String path, FileTypeEnum fileTypeEnum) {
        super(path, fileTypeEnum);
    }

    @Override
    protected void doWrite(Workbook workbook) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            WriteComponentContext componentContext = getDefaultComponentContext(workbook);
            componentContext.combine().write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeOutputStream(fos);
            ExcelUtils.closeWorkbook(workbook);
        }
    }

    private WriteComponentContext getDefaultComponentContext(Workbook workbook) {
        WriteComponentContext componentContext = new WriteComponentContext(workbook, headerDataList, contentDataList);
        return componentContext;
    }
}