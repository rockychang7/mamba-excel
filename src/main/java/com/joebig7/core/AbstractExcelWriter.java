package com.joebig7.core;

import com.joebig7.enums.FileTypeEnum;
import com.joebig7.exception.MambaExcelException;
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
 * @description 定义操作execel的模板
 */
public abstract class AbstractExcelWriter {

    private String path;

    public AbstractExcelWriter(String path) {
        this.path = path;
    }

    public void write() {
        write(path, XLSX);
    }

    public void write(String path, FileTypeEnum fileTypeEnum) {
        FileOutputStream fis = FileUtils.getFileOutputStream(path);
        Workbook workbook;
        if (fileTypeEnum == XLSX) {
            workbook = new XSSFWorkbook();
        } else if (fileTypeEnum == XLS) {
            workbook = new HSSFWorkbook();
        } else {
            throw new MambaExcelException("file type is not correct");
        }

        doWrite(fis, workbook);
    }

    abstract protected void doWrite(FileOutputStream fis, Workbook workbook);
}
