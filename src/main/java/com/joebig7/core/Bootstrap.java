package com.joebig7.core;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author JoeBig7
 * @date 2021/7/12 17:18:59
 * @demo
 */
public class Bootstrap {

    public static void main(String[] args) {

//        writeXls();


    }

    public static void writeXls() {

        try (FileOutputStream fos = new FileOutputStream(new File("D://test2.xls"))) {

            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

            HSSFSheet sheet = hssfWorkbook.createSheet("test");

            HSSFRow row = sheet.createRow(0);

            HSSFCell cell = row.createCell(0, CellType.STRING);

            cell.setCellValue("joebig7");

            hssfWorkbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void writeXlsx() {
        try (FileOutputStream fos = new FileOutputStream(new File("D://test.xlsx"))) {

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

            XSSFSheet sheet = xssfWorkbook.createSheet("test");

            XSSFRow row = sheet.createRow(0);

            XSSFCell cell = row.createCell(0, CellType.STRING);

            cell.setCellValue("joebig7");

            xssfWorkbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
