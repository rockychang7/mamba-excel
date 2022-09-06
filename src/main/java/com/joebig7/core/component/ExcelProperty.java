package com.joebig7.core.component;

import com.joebig7.core.data.HeaderData;
import com.joebig7.enums.FileTypeEnum;

import java.io.InputStream;
import java.util.List;

/**
 * @Author JoeBig7
 * @date 2021/11/18 19:07:57
 * @description excel property define
 */
public class ExcelProperty {

    protected List<HeaderData> headerDataList;

    protected String path;

    protected String suffix;

    protected FileTypeEnum fileTypeEnum;

    protected List<List<Object>> contentDataList;

    protected String sheetName = "Sheet1";

    protected InputStream inputStream;

    public List<HeaderData> getHeaderDataList() {
        return headerDataList;
    }

    public void setHeaderDataList(List<HeaderData> headerDataList) {
        this.headerDataList = headerDataList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileTypeEnum getFileTypeEnum() {
        return fileTypeEnum;
    }

    public void setFileTypeEnum(FileTypeEnum fileTypeEnum) {
        this.fileTypeEnum = fileTypeEnum;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<List<Object>> getContentDataList() {
        return contentDataList;
    }

    public void setContentDataList(List<List<Object>> contentDataList) {
        this.contentDataList = contentDataList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
