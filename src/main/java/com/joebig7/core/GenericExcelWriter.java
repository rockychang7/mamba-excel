package com.joebig7.core;

import com.joebig7.enums.FileTypeEnum;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author JoeBig7
 * @date 2021/7/16 16:39:56
 */
public class GenericExcelWriter extends AbstractExcelWriter {
    private String path;
    private String sheetName;

    public GenericExcelWriter(String path, String sheetName) {
        this(path, sheetName, FileTypeEnum.XLSX);
    }

    public GenericExcelWriter(String path, String sheetName, FileTypeEnum fileTypeEnum) {
        super(path, fileTypeEnum);
        this.path = path;
        this.sheetName = sheetName == null ? "default" : sheetName;
    }

    @Override
    protected void doWrite(FileOutputStream fis, Workbook workbook) {
        Sheet sheet = createSheet(workbook);
        Row row = createRow(sheet);


        try (FileOutputStream fos = new FileOutputStream(path)) {

            Cell cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("joebig7");
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Sheet createSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet(sheetName);
        return sheet;
    }



    protected Row createRow(Sheet sheet) {
        Row row = sheet.createRow(0);

        return row;
    }




}
