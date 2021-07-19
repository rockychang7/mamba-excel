package com.joebig7.core;

import com.joebig7.enums.FileTypeEnum;
import com.joebig7.exception.ExcelManipulationException;
import com.joebig7.utils.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

import static com.joebig7.enums.FileTypeEnum.XLS;
import static com.joebig7.enums.FileTypeEnum.XLSX;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:37:40
 * @description 定义操作excel的模板
 */
public abstract class AbstractExcelWriter {

    private String path;

    private FileTypeEnum fileTypeEnum;


    public AbstractExcelWriter(String path, FileTypeEnum fileTypeEnum) {
        this.path = path;
        this.fileTypeEnum = fileTypeEnum;
    }

    public void write() {
        write(path, fileTypeEnum);
    }

    public void write(String path, FileTypeEnum fileTypeEnum) {
        FileOutputStream fis = FileUtils.getFileOutputStream(path);
        Workbook workbook;
        if (fileTypeEnum == XLSX) {
            workbook = new XSSFWorkbook();
        } else if (fileTypeEnum == XLS) {
            workbook = new HSSFWorkbook();
        } else {
            throw new ExcelManipulationException("file type is not correct");
        }

        doWrite(fis, workbook);
    }

    abstract protected void doWrite(FileOutputStream fis, Workbook workbook);
}
