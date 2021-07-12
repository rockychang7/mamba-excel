package com.joebig7.core;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:58:04
 */
public class HssfWriter extends AbstractExcelWriter {

    public HssfWriter(String path) {
        super(path);
    }

    @Override
    protected void doWrite(FileOutputStream fis, Workbook workbook) {

    }
}
