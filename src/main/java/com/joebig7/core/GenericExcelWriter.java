package com.joebig7.core;

import com.joebig7.core.component.DefaultComponentContext;
import com.joebig7.enums.FileTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author JoeBig7
 * @date 2021/7/16 16:39:56
 * @description generic excel writer class
 */
public class GenericExcelWriter extends AbstractExcelWriter {
    private String path;

    public GenericExcelWriter(String path) {
        this(path, FileTypeEnum.XLSX);
    }


    public GenericExcelWriter(String path, FileTypeEnum fileTypeEnum) {
        super(path, fileTypeEnum);
        this.path = path;
    }

    @Override
    protected void doWrite(FileOutputStream fis, Workbook workbook) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            DefaultComponentContext componentContext = getDefaultComponentContext(workbook);
            componentContext.combine().write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DefaultComponentContext getDefaultComponentContext(Workbook workbook) {
        DefaultComponentContext componentContext = new DefaultComponentContext(workbook, headerDataList, contentDataList);
        return componentContext;
    }
}
