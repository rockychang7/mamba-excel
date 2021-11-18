package com.joebig7.core.factory;

import com.joebig7.enums.FileTypeEnum;
import com.joebig7.exception.ExcelManipulationException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

import static com.joebig7.enums.FileTypeEnum.XLS;
import static com.joebig7.enums.FileTypeEnum.XLSX;

/**
 * @Author JoeBig7
 * @date 2021/11/18 18:58:02
 */
public class WorkBookFactory {
    public static Workbook writeInstance(FileTypeEnum fileTypeEnum) {
        if (fileTypeEnum == XLSX) {
            return new XSSFWorkbook();
        } else if (fileTypeEnum == XLS) {
            return new HSSFWorkbook();
        } else {
            throw new ExcelManipulationException("file type is not supported");
        }
    }

    public static Workbook readInstance(FileInputStream fileInputStream,FileTypeEnum fileTypeEnum) {
        try {
            if (fileTypeEnum == XLSX) {
                return new XSSFWorkbook(fileInputStream);
            } else if (fileTypeEnum == XLS) {
                return new HSSFWorkbook(fileInputStream);
            } else {
                throw new ExcelManipulationException("file type is not supported");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
