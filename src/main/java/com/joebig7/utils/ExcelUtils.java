package com.joebig7.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

/**
 * @Author JoeBig7
 * @date 2021/11/25 16:58:47
 */
public class ExcelUtils {

    /**
     * 关闭workbook流
     *
     * @param workbook
     */
    public static void closeWorkbook(Workbook workbook) {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
