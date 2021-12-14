package com.joebig7.core.factory;

import com.joebig7.enums.FileTypeEnum;
import com.joebig7.exception.ExcelManipulationException;
import com.mamba.core.string.StringUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.joebig7.enums.FileTypeEnum.*;
import static com.mamba.core.string.StringUtils.*;

/**
 * @Author JoeBig7
 * @date 2021/11/18 18:58:02
 */
public class ExcelFactory {
    public static Workbook writeInstance(String path) {
        if (isSuffix(path, XLSX.getFileType())) {
            return new XSSFWorkbook();
        } else if (isSuffix(path, XLS.getFileType())) {
            return new HSSFWorkbook();
        } else {
            throw new ExcelManipulationException("file type is not supported");
        }
    }

    public static Workbook readInstance(InputStream inputStream, FileTypeEnum fileTypeEnum, String path) {
        try {
            if (fileTypeEnum == XLSX && isSuffix(path, XLSX.getFileType())) {
                return new XSSFWorkbook(inputStream);
            } else if (fileTypeEnum == XLS && isSuffix(path, XLS.getFileType())) {
                return new HSSFWorkbook(inputStream);
            } else {
                throw new ExcelManipulationException("file type is not supported");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
