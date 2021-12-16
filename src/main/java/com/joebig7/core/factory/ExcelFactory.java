package com.joebig7.core.factory;

import com.joebig7.exception.ExcelManipulationException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

import static com.joebig7.enums.FileTypeEnum.XLS;
import static com.joebig7.enums.FileTypeEnum.XLSX;
import static com.mamba.core.string.StringUtils.isSuffix;

/**
 * @Author JoeBig7
 * @date 2021/11/18 18:58:02
 * @descrition Excel相关对象工厂
 */
public class ExcelFactory {

    /**
     * 根据路径生成workbook写对象
     *
     * @param path
     * @return
     */
    public static Workbook writeInstance(String path) {
        if (isSuffix(path, XLSX.getFileType())) {
            return new XSSFWorkbook();
        } else if (isSuffix(path, XLS.getFileType())) {
            return new HSSFWorkbook();
        } else {
            throw new ExcelManipulationException("file type is not supported");
        }
    }

    /**
     * 根据路径生成workbook读取对象
     *
     * @param inputStream
     * @param path
     * @return
     */
    public static Workbook readInstance(InputStream inputStream, String path) {
        try {
            if (isSuffix(path, XLSX.getFileType())) {
                return new XSSFWorkbook(inputStream);
            } else if (isSuffix(path, XLS.getFileType())) {
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
