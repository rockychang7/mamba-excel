package com.joebig7.core;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:57:43
 */
public class XssfWriter extends AbstractExcelWriter {

    public XssfWriter(String path) {
        super(path);
    }

    @Override
    public void doWrite(FileOutputStream fis, Workbook workbook) {
        XSSFWorkbook xssfWorkbook = (XSSFWorkbook) workbook;

    }

}
