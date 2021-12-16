package com.joebig7.core;

import com.joebig7.core.component.context.ExcelContext;
import com.joebig7.core.factory.CsvFactory;
import com.joebig7.utils.CsvUtils;
import com.joebig7.utils.ExcelUtils;
import com.joebig7.utils.CommonFileUtils;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author JoeBig7
 * @date 2021/7/16 16:39:56
 * @description generic excel writer class
 */
public class GenericWriter extends AbstractWriter {

    public GenericWriter(String path) {
        super(path);
    }

    /**
     * 写出excel文件
     *
     * @param workbook
     */
    @Override
    protected void doExcelWrite(Workbook workbook) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            ExcelContext componentContext = getDefaultComponentContext(workbook);
            componentContext.combine().write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CommonFileUtils.closeOutputStream(fos);
            ExcelUtils.closeWorkbook(workbook);
        }
    }

    /**
     * 写出csv文件
     *
     * @param path
     */
    @Override
    protected void doCsvWrite(String path) {
        CSVPrinter csvPrinter = CsvFactory.writeInstance(path);
        try {
            CsvUtils.write(csvPrinter, headerDataList, contentDataList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CsvUtils.flush(csvPrinter);
            CsvUtils.close(csvPrinter);
        }
    }

    /**
     * 获取excel组件
     *
     * @param workbook
     * @return
     */
    private ExcelContext getDefaultComponentContext(Workbook workbook) {
        ExcelContext componentContext = new ExcelContext(workbook, headerDataList, contentDataList);
        return componentContext;
    }
}