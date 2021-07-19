package com.joebig7.core;

import com.joebig7.core.data.HeaderData;
import com.joebig7.enums.FileTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author JoeBig7
 * @date 2021/7/16 16:39:56
 */
public class GenericExcelWriter extends AbstractExcelWriter {
    private String path;
    private List<HeaderData> headerData;
    private List<Map<String, Object>> contentData;

    public GenericExcelWriter(String path, List<HeaderData> headerData, List<Map<String, Object>> contentData) {
        this(path, FileTypeEnum.XLSX, headerData, contentData);
    }

    public GenericExcelWriter(String path, List<Map<String, Object>> contentData) {
        this(path, FileTypeEnum.XLSX, contentData);
    }

    public GenericExcelWriter(String path, FileTypeEnum fileTypeEnum, List<Map<String, Object>> contentData) {
        super(path, fileTypeEnum);
        this.path = path;
        this.contentData = contentData;
    }


    public GenericExcelWriter(String path, FileTypeEnum fileTypeEnum, List<HeaderData> headerData, List<Map<String, Object>> contentData) {
        super(path, fileTypeEnum);
        this.path = path;
        this.headerData = headerData;
        this.contentData = contentData;
    }

    @Override
    protected void doWrite(FileOutputStream fis, Workbook workbook) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
//            DefaultComponentContext componentContext = new DefaultComponentContext(workbook);
//            componentContext.combine().write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
